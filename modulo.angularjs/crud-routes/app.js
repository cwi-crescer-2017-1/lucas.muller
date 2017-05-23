var app = angular.module('app', ['toastr', 'ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/instrutores', {
            templateUrl: 'templates/instrutores.html',
            routeName: 'instrutores'
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

//            TO-DO LIST
//  -----------------------------------
//  - Fazer verificações nos services.
//  - Fazer getAulasPorArrayDeIDs().
//  - Fazer aula tem vinculos no remover aula.

app.factory('instrutorService', function($http, $q, toastr, apiUrlBase, fotoUrlDefault) {
    // let instrutores = [
    //     {
    //         id: 0,
    //         nome: 'Bernardo',
    //         sobrenome: 'Rezende',
    //         idade: 30,
    //         email: 'bernardo@cwi.com.br',
    //         jaDeuAula: true,
    //         aula: ["0", "2"],
    //         fotoUrl: "http://fullmoonbrewwork.com/wp-content/uploads/2014/06/FMBW_Beers_Phuket-Lager-300x300.png"
    //     },
    //     {
    //         id: 1,
    //         nome: 'André',
    //         sobrenome: 'Nunes',
    //         idade: 35,
    //         email: 'andre.nunes@cwi.com.br',
    //         jaDeuAula: true,
    //         aula: ["4"],
    //         fotoUrl: "https://pedrotavars.files.wordpress.com/2012/02/moneysmiley.png"
    //     }
    // ];

    // let ultimoID = 1;

    function verificaNomeInstrutor(instrutor) {
        // return instrutores.some(e=> (e.nome + " " + e.sobrenome).toLowerCase().includes((instrutor.nome + " " + instrutor.sobrenome).toLowerCase()) && e.id !== instrutor.id);
        return false;
    };

    function verificaEmailInstrutor(instrutor) {
        // return instrutores.some(e=> e.email.toLowerCase().includes(instrutor.email.toLowerCase()) && e.id !== instrutor.id);
        return false;
    };

    function adicionarInstrutor(instrutor) {
        let promise = $q.defer();
        let nInstrutor = angular.copy(instrutor);
        nInstrutor.fotoUrl = nInstrutor.fotoUrl || fotoUrlDefault;
        if(verificaNomeInstrutor(instrutor)){
            toastr.warning("Instrutor já cadastrado!");
            promise.resolve(false);
        } else if(verificaEmailInstrutor(instrutor)) {
            toastr.warning("Email já está sendo utilizado!");
            promise.resolve(false);
        } else {
            $http.post(apiUrlBase + '/instrutores', nInstrutor)
                .then(()=>{ promise.resolve(true); }, () => { promise.resolve(false); });
        }
        return promise.promise;
    };

    function alterarInstrutor(instrutor) {
        let promise = $q.defer();
        if(verificaNomeInstrutor(instrutor)){
            toastr.warning("Instrutor já cadastrado!");
            promise.resolve(false);
        } 
        else if(verificaEmailInstrutor(instrutor)) {
            toastr.warning("Email já está sendo utilizado por outro instrutor!");
            promise.resolve(false);
        } else {
            $http.put(apiUrlBase + '/instrutores/' + instrutor.id, instrutor)
                .then(()=>{ promise.resolve(true); }, () => { promise.resolve(false); });
        }
        return promise.promise;
    };

    function removerInstrutorPorID(id) {
        let promise = $q.defer();
        $http.delete(apiUrlBase + '/instrutores/' +id)
                .then(()=>{ promise.resolve(true); }, () => { promise.resolve(false); });
        return promise.promise;
    };

    function getInstrutoresAPI() {
        return $http.get(apiUrlBase + '/instrutores');
    };

    return {
        getInstrutores: getInstrutoresAPI,
        adicionarInstrutor: adicionarInstrutor,
        alterarInstrutor: alterarInstrutor,
        removerInstrutorPorID: removerInstrutorPorID        
    };
});

app.factory('aulaService', function($http, $q, instrutorService, apiUrlBase, toastr) {
    // let aulas = [
    //     {id: 0, nome: 'OO'},
    //     {id: 1, nome: 'HTML e CSS'},
    //     {id: 2, nome: 'Javascript'},
    //     {id: 3, nome: 'AngularJS'},
    //     {id: 4, nome: 'Banco de Dados I'}
    // ];

    function verificaNomeAula(aula) {
        // return aulas.some(e=>e.nome.toLowerCase().includes(aula.nome.toLowerCase()) && e.id !== aula.id);
        return false;
    };

    function adicionarAula(aula) {
        let promise = $q.defer();
        let aulaJaExiste = verificaNomeAula(aula);
        if(aulaJaExiste) {
            toastr.warning("Aula já cadastrada!");
            promise.resolve(false);
        } else {
            $http.post(apiUrlBase + '/aulas', aula).then(response => {
                promise.resolve(true);
            }, () => { promise.resolve(false); });
        }
        return promise.promise;
    };

    function alterarAula(aula) {
        let promise = $q.defer();
        let aulaJaExiste = verificaNomeAula(aula);
        if(aulaJaExiste) {
            toast.warning("Aula já cadastrada!");
            promise.resolve(false);
        } else {
            $http.put(apiUrlBase + '/aulas/' + aula.id, aula)
                .then(()=> { promise.resolve(true) }, () => { promise.resolve(false); });
        }
        return promise.promise;
    };

    function removerAulaPorID(id) {
        let promise = $q.defer();
        // let aulaTemVinculos = instrutorService.getInstrutores().some(e=>e.aula.includes(""+id));
        let aulaTemVinculos = false;
        if(aulaTemVinculos) {
            toastr.warning("Não é possível excluir esta aula. Está sendo utilizada.");
            promise.resolve(false);
        } else {
            $http.delete(apiUrlBase + '/aulas/' + id).then(response => {
                promise.resolve(true)
            }, () => { promise.resolve(false); });
        }
        return promise.promise;
    };

    function getAulasPorArrayDeIDs(ids) {
        // if(angular.isUndefined(ids) || ids.length === 0)
        //     return [];
        // let arr = [];
        // ids.forEach(e => arr.push(aulas.find(a => a.id == e)));
        // return arr;
        if(angular.isUndefined(ids) || ids.length === 0)
            return [];
        else {
            getAulasAPI.then(response => {
                let aulas = response.data;
                return aulas.filter(e => ids.includes(e.id));
            });
        }
    };

    function getAulaPorID(id) {
        return $http.get(apiUrlBase + '/aulas/' + id);
    };

    function getAulasAPI() {
        return $http.get(apiUrlBase + '/aulas');
    };

    return {
        getAulas: getAulasAPI,
        getAulasPorArrayDeIDs: getAulasPorArrayDeIDs,
        adicionarAula: adicionarAula,
        alterarAula: alterarAula,
        removerAulaPorID: removerAulaPorID
    };
});

app.controller('MainCtrl', function($scope, $route, aulaService, instrutorService) {
    $scope.$route = $route;
    $scope.atualizarInstrutores = function() {
        instrutorService.getInstrutores().then(response => {
            $scope.instrutores = response.data;
            console.log("Instrutores atualizados!");
        });
    };
    $scope.atualizarInstrutores();

    $scope.atualizarAulas = function() {
        aulaService.getAulas().then(response => {
            $scope.aulas = response.data;
            console.log("Aulas atualizadas!");
        });
    };
    $scope.atualizarAulas();

    $scope.getAulasPorArray = aulaService.getAulasPorArrayDeIDs;
});

app.controller('AddInstrutorCtrl', function($scope, instrutorService, toastr) {
    $scope.adicionarInstrutor = function(instrutor) {
        instrutorService.adicionarInstrutor(instrutor).then((response) => {
            if(response === true) {
                $scope.novoInstrutor = {};
                $scope.formAddInstrutor.$setPristine();
                $scope.atualizarInstrutores();
                toastr.success("Instrutor adicionado com sucesso!");
            }
        });
    };
});

app.controller('AltInstrutorCtrl', function($scope, toastr, instrutorService) {
    $scope.alterarInstrutor = function(instrutor) {
        instrutorService.alterarInstrutor(instrutor).then(response => {
            if(response === true) {
                $scope.altInstrutor = {};
                $scope.formAltInstrutor.$setPristine();
                $scope.atualizarInstrutores();
                toastr.success("Instrutor alterado com sucesso!");
            }
        });
    };
});

app.controller('DelInstrutorCtrl', function($scope, toastr, instrutorService) {
    $scope.removerInstrutor = function(id) {
        instrutorService.removerInstrutorPorID(id).then(response => {
            if(response === true) {
                $scope.removerInstrutor.id = "";
                $scope.formExcInstrutor.$setPristine();
                $scope.atualizarInstrutores();
                toastr.success("Instrutor removido com sucesso!");
            }
        });
    }
});

app.controller('AddAulaCtrl', function($scope, aulaService, toastr) {
    $scope.adicionarAula = function(aula) {
        aulaService.adicionarAula(aula)
            .then(response => {
                if(response === true) {
                    $scope.novaAula = {};
                    $scope.formAddAula.$setPristine();
                    $scope.atualizarAulas();
                    toastr.success("Aula adicionada com sucesso!");
                }
            });
    };
});

app.controller('AltAulaCtrl', function($scope, aulaService, toastr) {
    $scope.alterarAula = function(aula) {
        aulaService.alterarAula(aula).then(response => {
            if(response === true) {
                $scope.altAula = {};
                $scope.formAltAula.$setPristine();
                $scope.atualizarAulas();
                toastr.success("Aula alterada com sucesso!");
            }
        });
    };
});

app.controller('DelAulaCtrl', function($scope, aulaService, toastr) {
    $scope.removerAula = function(id) {
        aulaService.removerAulaPorID(id).then(response => {
            if(response === true) {
                $scope.removerAula.id = "";
                $scope.formExcAula.$setPristine();
                $scope.atualizarAulas();
                toastr.success("Aula removida com sucesso!");
            }
        });
    };
});

app.filter('booleanToYesOrNo', function() {
    return function(boolean) {
        return boolean?"Sim":"Não";
    };
});