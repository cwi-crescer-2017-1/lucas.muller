angular.module('app')
.controller('PerfilCtrl', function($scope, $stateParams, $state, apiService, toastr) {
    $scope.id = $stateParams.id;
    $scope.dados = {};
    $scope.loading = false;
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
                apiService.getAmigos($scope.usuario.id).then((response) => {
                    $scope.amigos = response.data.some(usu => (usu.idusuario1.id == $scope.id|| usu.idusuario2.id == $scope.id) && usu.ativo == 1);
                    $scope.solicitacaoEnviada = response.data.some(usu => (usu.idusuario1.id == $scope.id || usu.idusuario2.id == $scope.id) && usu.ativo == 0);
                    $scope.loading = false;
                }, () => {
                    $scope.loading = false;
                });
            }
        }, (response) => {
            $scope.loading = false;
            toastr.error(response.status == 404 ? 'Usuário não existe' : 'Erro ao obter dados do usuário');
            if(response.status == 404)
                $scope.notFound = true;
        });
    }
    getUsuario();
});