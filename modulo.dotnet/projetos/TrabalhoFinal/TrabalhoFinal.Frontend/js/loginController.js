angular.module('app')
.controller('LoginCtrl', function($scope, authService, toastr) {
    $scope.loading = false;
    $scope.logar = function(usuario) {
        $scope.loading = true;
        authService.login(usuario).then((response) => {
            $scope.loading = false;
            toastr.success('Você foi logado com sucesso.', `Bem-vindo ${response.data.Nome}!`);
        }, () => {
            $scope.loading = false;
            toastr.error('Verifique seus dados.', 'Erro ao logar!');
        });
    };
});