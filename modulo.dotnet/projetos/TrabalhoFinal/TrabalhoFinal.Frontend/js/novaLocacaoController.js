angular.module('app')
.controller('NovaLocacaoCtrl', function(cliente, produtos, pacotes, opcionais, $scope, $filter, ApiService) {
    $scope.cliente = cliente.data;
    $scope.produtos = produtos.data;
    $scope.pacotes = pacotes.data;
    $scope.opcionais = opcionais.data;
    $scope.locacao = {};
    $scope.locacao.IdCliente = cliente.data.Id;
    $scope.carregando = false;
    $scope.mensagem = {
        titulo: "",
        texto: "",
        exibir: false,
        erro: false
    };
    $scope.locacaoEfetuada = false;

    $scope.gerarOrcamento = function(locacao) {
        $scope.carregando = true;
        $scope.mensagem.exibir = false;
        ApiService.locacao.gerarOrcamento(buildarLocacao(locacao)).then((response) => {
            $scope.mensagem = {
                titulo: "Orçamento previsto",
                texto: `O valor previsto para essa locação é de: ${$filter('currency')(response.data.PrecoFinalPrevisto, 'R$ ', 2)}`,
                exibir: true,
                erro: false
            };
            $scope.carregando = false;
        }, (response) => {
            $scope.mensagem = {
                titulo: "Erro ao gerar orçamento",
                texto: response.status == 400 ? response.data.Message : "Verifique seus dados e conexão",
                exibir: true,
                erro: true
            };
            $scope.carregando = false;
        });
    };

    $scope.locar = function(locacao) {
        $scope.carregando = true;
        $scope.mensagem.exibir = false;
        ApiService.locacao.locar(buildarLocacao(locacao)).then((response) => {
            $scope.mensagem = {
                titulo: "Locação efetuada com sucesso!",
                texto: `O valor previsto para essa locação é de: ${$filter('currency')(response.data.PrecoFinalPrevisto, 'R$ ', 2)}`,
                exibir: true,
                erro: false
            };
            $scope.locacaoEfetuada = true;
            $scope.carregando = false;
        }, (response) => {
            $scope.mensagem = {
                titulo: "Erro ao efetuar locação",
                texto: response.status == 400 ? response.data.Message : "Verifique seus dados e conexão",
                exibir: true,
                erro: true
            };
            $scope.carregando = false;
        });
    };

    function buildarLocacao() {
        var locacao = $scope.locacao;
        locacao.Opcionais = $scope.opcionais.filter(x => x.selecionado == true);
        console.log(locacao);
        return locacao;
    };
})