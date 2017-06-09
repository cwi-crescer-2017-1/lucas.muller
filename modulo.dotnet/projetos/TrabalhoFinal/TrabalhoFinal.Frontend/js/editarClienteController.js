angular.module('app')
.controller('EditarClienteCtrl', function(cliente, locacoes, $scope, $stateParams, ApiService, toastr) {
    $scope.cliente = cliente.data;
    $scope.locacoes = locacoes.data;
    $scope.cliente.Genero = cliente.data.Genero.toString();
    $scope.cliente.DataNascimento = new Date($scope.cliente.DataNascimento);
    $scope.salvar = function(cliente) {
        ApiService.clientes.editar(cliente.Id, cliente).then((response) => {
            toastr.success('Cliente alterado com sucesso!');
        }, () => {
            toastr.error('Erro ao alterar cliente.');
        });
    };
    $scope.devolver = devolver;
    
     function devolver(id) {
        ApiService.locacao.devolver(id).then(() => {
            toastr.success('Devolvido com sucesso!');
        }, () => {
            toastr.error('Erro ao devolver veículo!');
        });
    };
});