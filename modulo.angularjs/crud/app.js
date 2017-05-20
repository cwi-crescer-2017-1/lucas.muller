var app = angular.module('app', []);

app.controller('CrudCtrl', function($scope) {
    $scope.instrutores = [
        {
            nome: 'Bernardo',
            sobrenome: 'Rezende',
            idade: 30,
            email: 'bernardo@cwi.com.br',
            jaDeuAula: true,
            aula: 'OO'
        },
        {
            nome: 'André',
            sobrenome: 'Nunes',
            idade: 35,
            email: 'andre.nunes@cwi.com.br',
            jaDeuAula: true,
            aula: 'Banco de Dados I'
        }
    ];

    $scope.aulas = [
        'OO',
        'HTML e CSS',
        'Javascript',
        'AngularJS',
        'Banco de Dados I'
    ];

    $scope.adicionarInstrutor = function(instrutor) {
        $scope.instrutores.push(angular.copy(instrutor));
        $scope.novoInstrutor = {};
        $scope.formAddInstrutor.$setPristine();
    };

    $scope.removerInstrutor = function(index) {
        $scope.instrutores.splice(index, 1);
        $scope.removerInstrutor = {};
        $scope.formExcInstrutor.$setPristine();
    };
});

app.filter('booleanToYesOrNo', function() {
    return function(boolean) {
        return boolean?"Sim":"Não";
    };
});