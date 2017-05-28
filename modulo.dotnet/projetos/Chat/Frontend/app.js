var app = angular.module('chat', []);

app.constant('APIBaseUrl', 'http://localhost:56146');

app.factory('ChatService', function($http, APIBaseUrl) {
    return {
        getMensagens: () => $http.get(APIBaseUrl + '/api/mensagens?order=desc'),
        getMensagensComplexa: (limit, newerThan) =>  $http.get(`${APIBaseUrl}/api/mensagens?order=desc&limit=${limit}&newerThan=${newerThan}`),
        getMensagemPorId: (id) => $http.get(APIBaseUrl + '/api/mensagens/' + id),
        enviaMensagem: (data) => $http.post(APIBaseUrl + '/api/mensagens', data),
        getUsuarios: () => $http.get(APIBaseUrl + '/api/usuarios'),
        getUsuarioPorId: (id) => $http.get(APIBaseUrl + '/api/usuarios/' + id),
        cadastraUsuario: (data) => $http.post(APIBaseUrl + '/api/usuarios', data),
    };
});

app.controller('ChatCtrl', function($scope, ChatService) {
    $scope.mensagens = null;
    $scope.usuarios = null;
    $scope.usuario = localStorage.getItem("usuario_id");
    $scope.enviaMensagem = enviaMensagem;
    $scope.getMensagemAutor = getMensagemAutor;
    $scope.error = false;

    iniciaApp();

    // para buscar mensagens periodicamente
    setInterval(() => {
        buscaUsuarios();
        buscaMensagens();
    }, 1000);

    function getMensagemAutor(idAutor) { return $scope.usuarios.find(u => u.Id == idAutor); }
    function getUsuario() { return localStorage.getItem("usuario_id"); }

    function iniciaApp() {
        if($scope.usuario == null || $scope.usuario == 0) {
            var nome = prompt("Digite seu nome");
            var fotoUrl = prompt("Insira o link da sua foto");
            if(nome == null && fotoUrl == null) {
                localStorage.setItem("usuario_id", 0);
                $scope.usuario = null;
                return;
            }
            ChatService.cadastraUsuario({nome: nome, fotoUrl: fotoUrl})
                .then(response => {
                    localStorage.setItem("usuario_id", response.data.Id);
                    $scope.usuario = response.data.Id;
                }, response => { console.error("Erro ao cadastrar usuário!", response); });
        }
    }

    function enviaMensagem(texto) {
        if(getUsuario() != null && getUsuario() != 0) {
            var data = { texto: texto, idUsuarioAutor: getUsuario() };
            ChatService
                .enviaMensagem(data)
                .then(response => {
                    $scope.novaMensagem = null;
                }, response => { console.error("Erro ao enviar mensagem!", response); });
        } else {
            alert("Você não pode enviar mensagens sem se cadastrar!");
        }
    }
    
    function buscaMensagens() {
        if($scope.mensagens == null || $scope.mensagens.length == 0) {
            ChatService.getMensagens()
                .then(response => {
                    $scope.mensagens = response.data;
                    $scope.error = false;
                }, () => { $scope.error = true; });
        } else {
            ChatService.getMensagensComplexa(0, $scope.mensagens[0].Id)
                .then(response => {
                    $scope.mensagens = (response.data).concat($scope.mensagens);
                    $scope.error = false;
                }, () => { $scope.error = true; });
        }
    }

    function buscaUsuarios() {
        ChatService.getUsuarios()
            .then(response => {
                $scope.usuarios = response.data;
            });
    }
});