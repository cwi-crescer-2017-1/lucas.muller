var app = angular.module('app', ['toastr', 'ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/instrutores', {
            templateUrl: 'templates/instrutores.html',
            routeName: 'instrutores'
        })
        .when('/instrutores/:id', {
            controller: 'InstrutorCtrl',
            templateUrl: 'templates/instrutor.html',
            routeName: 'instrutor'
        })
        .when('/instrutores/adicionar', {
            controller: 'AddInstrutorCtrl',
            templateUrl: 'templates/instrutores-add.html',
            routeName: 'instrutores-add'
        })
        .when('/instrutores/alterar', {
            controller: 'AltInstrutorCtrl',
            templateUrl: 'templates/instrutores-alt.html',
            routeName: 'instrutores-alt'
        })
        .when('/instrutores/remover', {
            controller: 'DelInstrutorCtrl',
            templateUrl: 'templates/instrutores-del.html',
            routeName: 'instrutores-del'
        })
        .when('/aulas', {
            templateUrl: 'templates/aulas.html',
            routeName: 'aulas'
        })
        .when('/aulas/adicionar', {
            controller: 'AddAulaCtrl',
            templateUrl: 'templates/aulas-add.html',
            routeName: 'aulas-add'
        })
        .when('/aulas/alterar', {
            controller: 'AltAulaCtrl',
            templateUrl: 'templates/aulas-alt.html',
            routeName: 'aulas-alt'
        })
        .when('/aulas/remover', {
            controller: 'DelAulaCtrl',
            templateUrl: 'templates/aulas-del.html',
            routeName: 'aulas-del'
        })
        .otherwise({redirectTo: '/instrutores'});
});

app.constant('fotoUrlDefault', 'https://kokensupport.com/styles/simplicity_gray/theme/images/no_avatar.gif');
app.constant('apiUrlBase', 'http://localhost:3000');

app.factory('instrutorService', function($http, $q, toastr, apiUrlBase, fotoUrlDefault) {

    function adicionarInstrutor(instrutor) {
        let nInstrutor = angular.copy(instrutor);
        nInstrutor.fotoUrl = nInstrutor.fotoUrl || fotoUrlDefault;
        return $http.post(apiUrlBase + '/instrutores', nInstrutor);
    };

    function alterarInstrutor(instrutor) {
        return $http.put(apiUrlBase + '/instrutores/' + instrutor.id, instrutor);
    };

    function removerInstrutorPorID(id) {
        return $http.delete(apiUrlBase + '/instrutores/' +id);
    };

    function getInstrutoresAPI() {
        return $http.get(apiUrlBase + '/instrutores');
    };

    function getInstrutorPorId(id) {
        return $http.get(apiUrlBase + '/instrutores/' + id);
    };

    return {
        getInstrutores: getInstrutoresAPI,
        getInstrutorPorId: getInstrutorPorId,
        adicionarInstrutor: adicionarInstrutor,
        alterarInstrutor: alterarInstrutor,
        removerInstrutorPorID: removerInstrutorPorID        
    };
});

app.factory('aulaService', function($http, $q, instrutorService, apiUrlBase, toastr) {

    function adicionarAula(aula) {
        return $http.post(apiUrlBase + '/aulas', aula);
    };

    function alterarAula(aula) {
        return $http.put(apiUrlBase + '/aulas/' + aula.id, aula);
    };

    function removerAulaPorID(id) {
        return $http.delete(apiUrlBase + '/aulas/' + id);
    };

    function getAulaPorID(id) {
        return $http.get(apiUrlBase + '/aulas/' + id);
    };

    function getAulasAPI() {
        return $http.get(apiUrlBase + '/aulas');
    };

    return {
        getAulas: getAulasAPI,
        getAulaPorID: getAulaPorID,
        adicionarAula: adicionarAula,
        alterarAula: alterarAula,
        removerAulaPorID: removerAulaPorID
    };
});

app.controller('MainCtrl', function($scope, $route, toastr, aulaService, instrutorService) {
    $scope.$route = $route;
    $scope.toastr = toastr;
    $scope.atualizarInstrutores = function() {
        instrutorService.getInstrutores().then(response => {
            $scope.instrutores = response.data;
            console.log("Instrutores atualizados!");
        }, () => { toastr.error("Erro ao obter instrutores!"); });
    };
    $scope.atualizarInstrutores();

    $scope.atualizarAulas = function() {
        aulaService.getAulas().then(response => {
            $scope.aulas = response.data;
            console.log("Aulas atualizadas!");
        }, () => { toastr.error("Erro ao obter aulas!"); });
    };
    $scope.atualizarAulas();

    $scope.getAulasPorArray = (ids) => {
        let aulas = $scope.aulas;

        if(angular.isUndefined(aulas) || angular.isUndefined(ids) || !(ids instanceof Array) || ids.length === 0)
            return [];
        
        return aulas.filter(e=>ids.includes(""+e.id));
    };
});

app.controller('InstrutorCtrl', function($scope, $routeParams, instrutorService) {
    $scope.id = $routeParams.id;
    function getInstrutor() {
        instrutorService.getInstrutorPorId($scope.id).then(response => {
           $scope.instrutor = response.data;
        }, () => { $scope.instrutor = null; });
    };
    getInstrutor();
});

app.controller('AddInstrutorCtrl', function($scope, instrutorService, toastr) {
    $scope.adicionarInstrutor = function(instrutor) {
        instrutorService.adicionarInstrutor(instrutor).then((response) => {
            $scope.novoInstrutor = {};
            $scope.formAddInstrutor.$setPristine();
            $scope.atualizarInstrutores();
            toastr.success("Instrutor adicionado com sucesso!");
        }, () => { toastr.error("Houve um erro ao adicionar este instrutor."); });
    };
});

app.controller('AltInstrutorCtrl', function($scope, toastr, instrutorService) {
    $scope.alterarInstrutor = function(instrutor) {
        instrutorService.alterarInstrutor(instrutor).then(response => {
            $scope.altInstrutor = {};
            $scope.formAltInstrutor.$setPristine();
            $scope.atualizarInstrutores();
            toastr.success("Instrutor alterado com sucesso!");
        }, () => { toastr.error("Houve um erro ao alterar este instrutor."); });
    };
});

app.controller('DelInstrutorCtrl', function($scope, toastr, instrutorService) {
    $scope.removerInstrutor = function(id) {
        instrutorService.removerInstrutorPorID(id).then(response => {
            $scope.removerInstrutor.id = "";
            $scope.formExcInstrutor.$setPristine();
            $scope.atualizarInstrutores();
            toastr.success("Instrutor removido com sucesso!");
        }, () => { toastr.error("Houve um erro ao remover este instrutor."); });
    }
});

app.controller('AddAulaCtrl', function($scope, aulaService, toastr) {
    $scope.adicionarAula = function(aula) {
        aulaService.adicionarAula(aula).then(response => {
            $scope.novaAula = {};
            $scope.formAddAula.$setPristine();
            $scope.atualizarAulas();
            toastr.success("Aula adicionada com sucesso!");
        }, () => { toastr.error("Houve um erro ao adicionar esta aula."); });
    };
});

app.controller('AltAulaCtrl', function($scope, aulaService, toastr) {
    $scope.alterarAula = function(aula) {
        aulaService.alterarAula(aula).then(response => {
            $scope.altAula = {};
            $scope.formAltAula.$setPristine();
            $scope.atualizarAulas();
            toastr.success("Aula alterada com sucesso!");
        }, () => { toastr.error("Houve um erro ao alterar esta aula."); });
    };
});

app.controller('DelAulaCtrl', function($scope, aulaService, toastr) {
    $scope.removerAula = function(id) {
        aulaService.removerAulaPorID(id).then(response => {
            $scope.removerAula.id = "";
            $scope.formExcAula.$setPristine();
            $scope.atualizarAulas();
            toastr.success("Aula removida com sucesso!");
        }, () => { toastr.error("Houve um erro ao remover esta aula."); });
    };
});

app.filter('booleanToYesOrNo', function() {
    return function(boolean) {
        return boolean?"Sim":"Não";
    };
});