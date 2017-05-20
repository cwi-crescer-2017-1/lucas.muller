var app = angular.module('app', []);

app.controller('CrudCtrl', function($scope) {
    $scope.instrutores = [
        {
            id: 0,
            nome: 'Bernardo',
            sobrenome: 'Rezende',
            idade: 30,
            email: 'bernardo@cwi.com.br',
            jaDeuAula: true,
            aula: ["0", "2"]
        },
        {
            id: 1,
            nome: 'André',
            sobrenome: 'Nunes',
            idade: 35,
            email: 'andre.nunes@cwi.com.br',
            jaDeuAula: true,
            aula: ["4"]
        }
    ];

    $scope.aulas = [
        {id: 0, nome: 'OO'},
        {id: 1, nome: 'HTML e CSS'},
        {id: 2, nome: 'Javascript'},
        {id: 3, nome: 'AngularJS'},
        {id: 4, nome: 'Banco de Dados I'}
    ];

    $scope.adicionarInstrutor = function(instrutor) {
        let nInstrutor = angular.copy(instrutor);
        let instrutores = $scope.instrutores;
        nInstrutor.id = instrutores[instrutores.length-1].id + 1;
        $scope.instrutores.push(nInstrutor);
        $scope.novoInstrutor = {};
        $scope.formAddInstrutor.$setPristine();
        alert("Instrutor adicionado com sucesso!");
    };

    $scope.removerInstrutor = function(id) {
        let index = $scope.instrutores.findIndex(e => e.id == id);
        if(index === -1) { 
            alert("Erro interno ao remover instrutor!");
            return;
        }
        $scope.instrutores.splice(index, 1);
        $scope.removerInstrutor.id = "";
        $scope.formExcInstrutor.$setPristine();
        alert("Instrutor removido com sucesso!");
    };

    $scope.adicionarAula = function(aula) {
        let nAula = angular.copy(aula);
        let aulas = $scope.aulas
        let aulaJaExiste = aulas.some(e=>e.nome.toLowerCase().includes(aula.nome.toLowerCase()));
        if(aulaJaExiste) {
            alert("Aula já cadastrada!");
            return;
        }
        nAula.id = aulas[aulas.length-1].id + 1;
        $scope.aulas.push(nAula);
        $scope.novaAula = {};
        $scope.formAddAula.$setPristine();
        alert("Aula adicionada com sucesso!");
    };
    
    $scope.removerAula = function(id) {
        var aulaTemVinculos = $scope.instrutores.some(e=>e.aula.includes(""+id));
        if(aulaTemVinculos) {
            alert("Não é possível excluir esta aula. Está sendo utilizada.");
            return;
        } else {
            var index = $scope.aulas.findIndex(e => e.id == id);
            if(index === -1) {
                alert("Erro interno ao remover aula!");
                return;
            }
            $scope.aulas.splice(index, 1);
            $scope.removerAula.id = "";
            $scope.formExcAula.$setPristine();
            alert("Aula removida com sucesso!");
        }
    };

    $scope.getAulas = function(ids) {
        let arr = [];
        ids.forEach(e => arr.push($scope.aulas.find(a => a.id == e)));
        return arr;
    };
});

app.filter('booleanToYesOrNo', function() {
    return function(boolean) {
        return boolean?"Sim":"Não";
    };
});