angular.module('app')
.controller('ContaCtrl', function($scope, apiService, toastr) {
    $scope.usuario = angular.copy($scope.usuario);
    $scope.usuario.dataNascimento = new Date($scope.usuario.dataNascimento);
    console.log($scope.usuario);
    $scope.alterar = function(usuario) {
        apiService.alterarUsuario(usuario).then(() => {
            toastr.success('Alteração bem sucedida');
        }, () => {
            toastr.error('Erro ao alterar sua conta');
        });
    };
});