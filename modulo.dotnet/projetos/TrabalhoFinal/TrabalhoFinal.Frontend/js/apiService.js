angular.module('app')
.constant('ApiBaseUrl', 'http://localhost:64885/api')
.factory('ApiService', function(ApiBaseUrl, $http) {
    var clientes = {
        obter: (id) => $http.get(`${ApiBaseUrl}/clientes/${id}`),
        procurar: (nome) => $http.get(`${ApiBaseUrl}/clientes/${nome}`),
        novo: (cliente) => $http.post(`${ApiBaseUrl}/clientes`, cliente),
        editar: (id, cliente) => $http.put(`${ApiBaseUrl}/clientes/${id}`, cliente),
        locacoes: (id) => $http.get(`${ApiBaseUrl}/clientes/${id}/locacoes`)
    };

    var itens = {
        obterProdutos: () => $http.get(`${ApiBaseUrl}/produtos`),
        obterPacotes: () => $http.get(`${ApiBaseUrl}/pacotes`),
        obterOpcionais: () => $http.get(`${ApiBaseUrl}/opcionais`),
    };

    var locacao = {
        gerarOrcamento: (locacao) => $http.post(`${ApiBaseUrl}/locacoes/orcamento`, locacao),
        locar: (locacao) => $http.post(`${ApiBaseUrl}/locacoes`, locacao),
        devolver: (id) => $http.get(`${ApiBaseUrl}/locacoes/${id}/devolver`),
    };

    var relatorios = {
        atrasos: () => $http.get(`${ApiBaseUrl}/locacoes/relatorios/atrasos`),
        mensal: (data) => $http.get(`${ApiBaseUrl}/locacoes/relatorios/mensal/${data.toISOString().split('T')[0]}`)
    }

    return {
        clientes: clientes,
        itens: itens,
        locacao: locacao,
        relatorios: relatorios
    };
});