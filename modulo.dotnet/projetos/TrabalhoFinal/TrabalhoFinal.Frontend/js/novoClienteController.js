angular.module('app')
.controller('NovoClienteCtrl', function($scope, ApiService, $state, toastr) {
    $scope.salvar = function(cliente) {
        ApiService.clientes.novo(cliente).then((response) => {
            console.log(response.data);
            toastr.success('Cliente criado com sucesso!');
            $state.go('adm.editarCliente', {id: response.data.Id});
        }, () => {
            toastr.error('Erro ao criar cliente.');
        });
    };
});