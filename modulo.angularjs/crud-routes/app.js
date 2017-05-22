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

app.factory('instrutorService', function(toastr, fotoUrlDefault) {
    let instrutores = [
        {
            id: 0,
            nome: 'Bernardo',
            sobrenome: 'Rezende',
            idade: 30,
            email: 'bernardo@cwi.com.br',
            jaDeuAula: true,
            aula: ["0", "2"],
            fotoUrl: "http://fullmoonbrewwork.com/wp-content/uploads/2014/06/FMBW_Beers_Phuket-Lager-300x300.png"
        },
        {
            id: 1,
            nome: 'André',
            sobrenome: 'Nunes',
            idade: 35,
            email: 'andre.nunes@cwi.com.br',
            jaDeuAula: true,
            aula: ["4"],
            fotoUrl: "https://pedrotavars.files.wordpress.com/2012/02/moneysmiley.png"
        }
    ];

    let ultimoID = 1;

    function verificaNomeInstrutor(instrutor) {
        return instrutores.some(e=> (e.nome + " " + e.sobrenome).toLowerCase().includes((instrutor.nome + " " + instrutor.sobrenome).toLowerCase()) && e.id !== instrutor.id);
    };

    function verificaEmailInstrutor(instrutor) {
        return instrutores.some(e=> e.email.toLowerCase().includes(instrutor.email.toLowerCase()) && e.id !== instrutor.id);
    };

    function adicionarInstrutor(instrutor) {
        let nInstrutor = angular.copy(instrutor);
        nInstrutor.id = angular.copy(++ultimoID);
        nInstrutor.fotoUrl = nInstrutor.fotoUrl || fotoUrlDefault;
        if(verificaNomeInstrutor(instrutor)){
            toastr.warning("Instrutor já cadastrado!");
            return false;
        } 
        if(verificaEmailInstrutor(instrutor)) {
            toastr.warning("Email já está sendo utilizado!");
            return false;
        }
        instrutores.push(nInstrutor);
        toastr.success("Instrutor adicionado com sucesso!");
        return true;
    };

    function alterarInstrutor(instrutor) {
        let nInstrutor = angular.copy(instrutor);
        if(verificaNomeInstrutor(instrutor)){
            toastr.warning("Instrutor já cadastrado!");
            return false;
        } 
        if(verificaEmailInstrutor(instrutor)) {
            toastr.warning("Email já está sendo utilizado por outro instrutor!");
            return false;
        }
        let index = instrutores.findIndex(e => e.id == instrutor.id);
        if(index === -1) {
            toastr.error("Erro interno ao alterar instrutor!");
            return false;
        }
        instrutores[index] = nInstrutor;
        toastr.success("Instrutor alterado com sucesso!");
        return true;
    };

    function removerInstrutorPorID(id) {
        let index = instrutores.findIndex(e => e.id == id);
        if(index === -1) { 
            toastr.error("Erro interno ao remover instrutor!");
            return false;
        }
        instrutores.splice(index, 1);
        toastr.success("Instrutor removido com sucesso!");
        return true;
    };

    return {
        getInstrutores: () => instrutores,
        adicionarInstrutor: adicionarInstrutor,
        alterarInstrutor: alterarInstrutor,
        removerInstrutorPorID: removerInstrutorPorID        
    };
});

app.factory('aulaService', function(instrutorService, toastr) {
    let aulas = [
        {id: 0, nome: 'OO'},
        {id: 1, nome: 'HTML e CSS'},
        {id: 2, nome: 'Javascript'},
        {id: 3, nome: 'AngularJS'},
        {id: 4, nome: 'Banco de Dados I'}
    ];

    let ultimoID = 4;

    function verificaNomeAula(aula) {
        return aulas.some(e=>e.nome.toLowerCase().includes(aula.nome.toLowerCase()) && e.id !== aula.id);
    };

    function adicionarAula(aula) {
        let nAula = angular.copy(aula);
        let aulaJaExiste = verificaNomeAula(aula);
        if(aulaJaExiste) {
            toastr.warning("Aula já cadastrada!");
            return false;
        }
        nAula.id = angular.copy(++ultimoID);
        aulas.push(nAula);
        toastr.success("Aula adicionada com sucesso!");
        return true;
    };

    function alterarAula(aula) {
        let nAula = angular.copy(aula);
        let aulaJaExiste = verificaNomeAula(aula);
        if(aulaJaExiste) {
            alert("Aula já cadastrada!");
            return false;
        }
        let index = aulas.findIndex(e => e.id == aula.id);
        if(index === -1) {
            toastr.error("Erro interno ao alterar aula!");
            return false;
        }
        aulas[index] = nAula;
        toastr.success("Aula alterada com sucesso!");
        return true;
    };

    function removerAulaPorID(id) {
        let aulaTemVinculos = instrutorService.getInstrutores().some(e=>e.aula.includes(""+id));
        if(aulaTemVinculos) {
            toastr.warning("Não é possível excluir esta aula. Está sendo utilizada.");
            return false;
        } else {
            var index = aulas.findIndex(e => e.id == id);
            if(index === -1) {
                toastr.error("Erro interno ao remover aula!");
                return false;
            }
            aulas.splice(index, 1);
            toastr.success("Aula removida com sucesso!");
            return true;
        }
    };

    function getAulasPorArrayDeIDs(ids) {
        if(angular.isUndefined(ids) || ids.length === 0)
            return [];
        let arr = [];
        ids.forEach(e => arr.push(aulas.find(a => a.id == e)));
        return arr;
    };

    return {
        getAulas: ()=> aulas,
        getAulasPorArrayDeIDs: getAulasPorArrayDeIDs,
        adicionarAula: adicionarAula,
        alterarAula: alterarAula,
        removerAulaPorID: removerAulaPorID
    };
});

app.controller('MainCtrl', function($scope, $route, aulaService, instrutorService) {
    $scope.$route = $route;
    $scope.instrutores = instrutorService.getInstrutores();
    $scope.aulas = aulaService.getAulas();
    $scope.getAulasPorArray = aulaService.getAulasPorArrayDeIDs;
});

app.controller('AddInstrutorCtrl', function($scope, instrutorService) {
    $scope.adicionarInstrutor = function(instrutor) {
        if(instrutorService.adicionarInstrutor(instrutor) === true) {
            $scope.novoInstrutor = {};
            $scope.formAddInstrutor.$setPristine();
        }
    };
});

app.controller('AltInstrutorCtrl', function($scope, instrutorService) {
    $scope.alterarInstrutor = function(instrutor) {
        if(instrutorService.alterarInstrutor(instrutor) === true) {
            $scope.altInstrutor = {};
            $scope.formAltInstrutor.$setPristine();
        }
    };
});

app.controller('DelInstrutorCtrl', function($scope, instrutorService) {
    $scope.removerInstrutor = function(id) {
        if(instrutorService.removerInstrutorPorID(id) === true) {
            $scope.removerInstrutor.id = "";
            $scope.formExcInstrutor.$setPristine();
        }
    }
});

app.controller('AddAulaCtrl', function($scope, aulaService) {
    $scope.adicionarAula = function(aula) {
        if(aulaService.adicionarAula(aula) === true) {
            $scope.novaAula = {};
            $scope.formAddAula.$setPristine();
        }
    };
});

app.controller('AltAulaCtrl', function($scope, aulaService) {
    $scope.alterarAula = function(aula) {
        if(aulaService.alterarAula(aula) === true) {
            $scope.altAula = {};
            $scope.formAltAula.$setPristine();
        }
    };
});

app.controller('DelAulaCtrl', function($scope, aulaService) {
    $scope.removerAula = function(id) {
        if(aulaService.removerAulaPorID(id) === true) {
            $scope.removerAula.id = "";
            $scope.formExcAula.$setPristine();
        }
    };
});

app.filter('booleanToYesOrNo', function() {
    return function(boolean) {
        return boolean?"Sim":"Não";
    };
});