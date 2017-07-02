angular.module('app')
.controller('LoginCtrl', function($scope, $http, authService, toastr) {
    $scope.carregando = false;

    $scope.login = function(usuario) {
        $scope.carregando = true;
        authService.login(usuario).then((response) => {
            $scope.carregando = false;
            toastr.success('Você foi logado(a)', `Bem-vindo(a) ${response.data.nome}`)
        }, (response) => {
            $scope.carregando = false;
            console.log(response);
            toastr.error('Verifique seus dados e conexão', 'Erro ao logar');
        });
    };
});