angular.module('app')
.controller('PerfilCtrl', function($scope, $sce, $stateParams, $state, apiService, toastr) {
    $scope.id = $stateParams.id;
    $scope.dados = {};
    $scope.loading = false;
    $scope.loadingPosts = false;
    $scope.notFound = false;
    $scope.amigos = undefined;
    $scope.solicitacaoEnviada = false;
    $scope.enviandoSolicitacao = false;

    $scope.enviarSolicitacaoAmizade = function() {
        if($scope.enviandoSolicitacao)
            return;
        $scope.enviandoSolicitacao = true;
        apiService.novaAmizade($scope.id).then(() => {
            $scope.solicitacaoEnviada = true;
            $scope.enviandoSolicitacao = false;
        }, () => {
            toastr.error('Erro ao enviar solicitação de amizade');
            $scope.enviandoSolicitacao = false;
        });
    };

    $scope.getTextoFormatado = function(texto) {
        texto = markdown.toHTML(texto);
        texto = texto.replace(/(https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*))/g, `<a href="$1" target="_blank">$1</a>`);
        return $sce.trustAsHtml(texto);
    };

    function getPostsUsuario() {
        $scope.loadingPosts = true;
        apiService.getPostsUsuario($scope.id).then((response) => {
            $scope.loadingPosts = false;
            $scope.posts = response.data;
        }, () => {
            $scope.loadingPosts = false;
            toastr.error('Não foi possível obter os posts do usuário');
        });
    }

    function getUsuario() {
        $scope.loading = true;
        $scope.notFound = false;
        apiService.getUsuario($scope.id).then((response) => {
            $scope.dados = response.data;
            $state.current.data.pageTitle = response.data.nome;
            $scope.notFound = false;
            if($scope.usuario.id == $scope.id) {
                $scope.loading = false;
            } else {
                // verifica amizade
                apiService.getAmigos($scope.usuario.id).then((response) => {
                    $scope.amigos = response.data.some(usu => (usu.idusuario1.id == $scope.id|| usu.idusuario2.id == $scope.id) && usu.ativo == 1);
                    $scope.solicitacaoEnviada = response.data.some(usu => (usu.idusuario1.id == $scope.id || usu.idusuario2.id == $scope.id) && usu.ativo == 0);
                    // $scope.solicitaoRecebida = response.data.some(usu => usu.idusuario2.id == $scope.usuario.id && usu.ativo == 0);
                    $scope.loading = false;
                }, () => {
                    $scope.loading = false;
                });
            }
            getPostsUsuario();
        }, (response) => {
            $scope.loading = false;
            toastr.error(response.status == 404 ? 'Usuário não existe' : 'Erro ao obter dados do usuário');
            if(response.status == 404)
                $scope.notFound = true;
        });
    }
    getUsuario();
});