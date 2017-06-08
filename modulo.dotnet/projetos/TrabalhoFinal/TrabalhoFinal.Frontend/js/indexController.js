angular.module('app')
.controller('IndexCtrl', function(ApiService, $state, authService, locacoesAtrasadas, toastr, $scope) {
    $scope.clientes = null;
    $scope.usuario = authService.getUsuario();
    $scope.locacoesAtrasadas = locacoesAtrasadas.data;
    $scope.locacoesRelMensal = null;
    $scope.somaLocacoesRelMensal = 0;
    $scope.buscando = false;
    $scope.buscandoRelMensal = false;
    $scope.buscar = function(nome) {
        $scope.buscando = true;
        ApiService.clientes.procurar(nome).then((response)=> {
            $scope.clientes = response.data;
            $scope.buscando = false;
        }, () => {
            toastr.error('Erro ao procurar cliente.');
            $scope.buscando = false;
        });
    };
    $scope.obterRelatorioMensal = function(data) {
        $scope.buscandoRelMensal = true;
        ApiService.relatorios.mensal(data).then((response) => {
            $scope.locacoesRelMensal = response.data;
            var soma = 0;
            response.data.forEach(x => {
                soma += x.PrecoFinalEfetivo;
            });
            $scope.buscandoRelMensal = false;
            $scope.somaLocacoesRelMensal = soma;
        }, () => {
            toastr.error('Erro ao obter relatório.');
            $scope.buscandoRelMensal = false;
        });
    };
    $scope.devolver = function(id, clienteId) {
        ApiService.locacao.devolver(id).then((response) => {
            toastr.success('Veículo devolvido com sucesso!');
            $state.go('adm.editarCliente', {id: clienteId});
        }, () => {
            toastr.error('Erro ao devolver veículo.');
        })
    };
});