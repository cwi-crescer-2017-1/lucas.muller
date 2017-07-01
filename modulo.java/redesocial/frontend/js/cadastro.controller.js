angular.module('app')
.controller('CadastroCtrl', function($scope, $http, $state, apiService, toastr) {
    $scope.carregando = false;

    $scope.cadastrar = function(usuario) {
        $scope.carregando = true;
        apiService.cadastrarUsuario(usuario).then((usuario) => {
            $scope.carregando = false;
            toastr.success('Agora faça login', 'Cadastrado com sucesso');
            $state.go('login');
        }, (response) => {
            $scope.carregando = false;
            toastr.error(response.data.message || 'Verifique seus dados e conexão', 'Erro ao cadastrar');
        })
    }
});