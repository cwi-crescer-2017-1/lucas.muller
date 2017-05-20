var app = angular.module('app', []);

app.constant('fotoUrlDefault', 'https://kokensupport.com/styles/simplicity_gray/theme/images/no_avatar.gif');

app.controller('CrudCtrl', function($scope, fotoUrlDefault) {
    $scope.instrutores = [
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
        nInstrutor.fotoUrl = nInstrutor.fotoUrl || fotoUrlDefault;
        if(verificaNomeInstrutor(nInstrutor.nome + " " + nInstrutor.sobrenome)){
            alert("Instrutor já cadastrado!");
            return;
        } 
        if(verificaEmailInstrutor(nInstrutor.email)) {
            alert("Email já está sendo utilizado!");
            return;
        }
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

    function verificaNomeInstrutor(nome) {
        return $scope.instrutores.some(e=> (e.nome.toLowerCase() + " " + e.sobrenome.toLowerCase()).includes(nome.toLowerCase()));
    };

    function verificaEmailInstrutor(email) {
        return $scope.instrutores.some(e=> e.email.toLowerCase().includes(email.toLowerCase()));
    };

    $scope.adicionarAula = function(aula) {
        let nAula = angular.copy(aula);
        let aulas = $scope.aulas;
        let aulaJaExiste = verificaNomeAula(aula.nome);
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

    $scope.alterarAula = function(aula) {
        let nAula = angular.copy(aula);
        let aulas = $scope.aulas;
        let aulaJaExiste = verificaNomeAula(aula.nome);
        if(aulaJaExiste) {
            alert("Aula já cadastrada!");
            return;
        }
        let index = aulas.findIndex(e => e.id == aula.id);
        if(index === -1) {
            alert("Erro interno ao alterar aula!");
            return;
        }
        $scope.aulas[index] = nAula;
        $scope.altAula = {};
        $scope.formAltAula.$setPristine();
        alert("Aula alterada com sucesso!");
    };

    function verificaNomeAula(nome) {
        return $scope.aulas.some(e=>e.nome.toLowerCase().includes(nome.toLowerCase()));
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

    $scope.getInstrutorById = function(id) {
        return angular.copy($scope.instrutores.find(e => e.id == id));
    };

    $scope.getAulaById = function(id) {
        return angular.copy($scope.aulas.find(e => e.id == id));
    };

    $scope.getAulasPorArray = function(ids) {
        if(angular.isUndefined(ids) || ids.length === 0)
            return [];
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