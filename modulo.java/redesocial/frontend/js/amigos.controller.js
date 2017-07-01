angular.module('app')
.controller('AmigosCtrl', function($scope, apiService, toastr) {
    $scope.obtendoAmigos = false;
    $scope.erro = false;
    $scope.amigos = [];
    $scope.solicitacoes = [];

    $scope.getAmigo = function(amizade) {
        return (amizade.idusuario1.id == $scope.usuario.id) ? amizade.idusuario2 : amizade.idusuario1;
    };

    $scope.aceitarAmizade = function(amizade) {
        apiService.aceitarAmizade(amizade.id).then(() => {
            amizade.ativo = 1;
            toastr.success('Solicitação aceita');
            $scope.solicitacoes.splice($scope.solicitacoes.findIndex(e => e.id == amizade.id), 1);
            $scope.amigos.unshift(amizade);
        }, () => {
            toastr.error('Erro ao aceitar amizade');
        });
    };

    $scope.rejeitarAmizade = function(amizade) {
        apiService.rejeitarAmizade(amizade.id).then(() => {
            amizade.rejeitada = true;
        }, () => {
            toastr.error('Erro ao rejeitar amizade');
        });
    }

    getAmigos();
    function getAmigos() {
        $scope.obtendoAmigos = true;
        $scope.erro = false;
        apiService.getAmigos().then((response) => {
            let amigos = response.data;
            $scope.amigos = amigos.filter(a => a.ativo == 1);
            $scope.solicitacoes = amigos.filter(a => a.ativo == 0 && a.idusuario2.id == $scope.usuario.id);
            $scope.obtendoAmigos = false;
        }, () => {
            $scope.erro = true;
            $scope.obtendoAmigos = false;
            toastr.error('Erro ao obter amigos');
        });
    }
});