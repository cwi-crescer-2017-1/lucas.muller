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
            aula: [0, 2]
        },
        {
            id: 1,
            nome: 'André',
            sobrenome: 'Nunes',
            idade: 35,
            email: 'andre.nunes@cwi.com.br',
            jaDeuAula: true,
            aula: [4]
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
        var nInstrutor = angular.copy(instrutor);
        nInstrutor.id = $scope.instrutores.length;
        $scope.instrutores.push(nInstrutor);
        $scope.novoInstrutor = {};
        $scope.formAddInstrutor.$setPristine();
    };

    $scope.removerInstrutor = function(index) {
        $scope.instrutores.splice(index, 1);
        $scope.removerInstrutor = {};
        $scope.formExcInstrutor.$setPristine();
    };

    $scope.getAulas = function(ids) {
        var arr = [];
        ids.forEach(e => arr.push($scope.aulas[e]));
        return arr;
    }
});

app.filter('booleanToYesOrNo', function() {
    return function(boolean) {
        return boolean?"Sim":"Não";
    };
});