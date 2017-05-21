var app = angular.module('app', ['toastr']);

app.constant('fotoUrlDefault', 'https://kokensupport.com/styles/simplicity_gray/theme/images/no_avatar.gif');

app.controller('CrudCtrl', function($scope, fotoUrlDefault, toastr) {
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
        if(verificaNomeInstrutor(instrutor)){
            toastr.warning("Instrutor já cadastrado!");
            return;
        } 
        if(verificaEmailInstrutor(instrutor)) {
            toastr.warning("Email já está sendo utilizado!");
            return;
        }
        $scope.instrutores.push(nInstrutor);
        $scope.novoInstrutor = {};
        $scope.formAddInstrutor.$setPristine();
        toastr.success("Instrutor adicionado com sucesso!");
    };

    $scope.alterarInstrutor = function(instrutor) {
        let nInstrutor = angular.copy(instrutor);
        let instrutores = $scope.instrutores;
        if(verificaNomeInstrutor(instrutor)){
            toastr.warning("Instrutor já cadastrado!");
            return;
        } 
        if(verificaEmailInstrutor(instrutor)) {
            toastr.warning("Email já está sendo utilizado por outro instrutor!");
            return;
        }
        let index = instrutores.findIndex(e => e.id == instrutor.id);
        if(index === -1) {
            toastr.error("Erro interno ao alterar instrutor!");
            return;
        }
        $scope.instrutores[index] = nInstrutor;
        //$scope.altInstrutorSelectedId = "";
        $scope.altInstrutor = {};
        $scope.formAltInstrutor.$setPristine();
        toastr.success("Instrutor alterado com sucesso!");
    };

    $scope.removerInstrutor = function(id) {
        let index = $scope.instrutores.findIndex(e => e.id == id);
        if(index === -1) { 
            toastr.error("Erro interno ao remover instrutor!");
            return;
        }
        $scope.instrutores.splice(index, 1);
        $scope.removerInstrutor.id = "";
        $scope.formExcInstrutor.$setPristine();
        toastr.success("Instrutor removido com sucesso!");
    };

    function verificaNomeInstrutor(instrutor) {
        return $scope.instrutores.some(e=> (e.nome + " " + e.sobrenome).toLowerCase().includes((instrutor.nome + " " + instrutor.sobrenome).toLowerCase()) && e.id !== instrutor.id);
    };

    function verificaEmailInstrutor(instrutor) {
        return $scope.instrutores.some(e=> e.email.toLowerCase().includes(instrutor.email.toLowerCase()) && e.id !== instrutor.id);
    };

    $scope.adicionarAula = function(aula) {
        let nAula = angular.copy(aula);
        let aulas = $scope.aulas;
        let aulaJaExiste = verificaNomeAula(aula.nome);
        if(aulaJaExiste) {
            toastr.warning("Aula já cadastrada!");
            return;
        }
        nAula.id = aulas[aulas.length-1].id + 1;
        $scope.aulas.push(nAula);
        $scope.novaAula = {};
        $scope.formAddAula.$setPristine();
        toastr.success("Aula adicionada com sucesso!");
    };

    $scope.alterarAula = function(aula) {
        let nAula = angular.copy(aula);
        let aulas = $scope.aulas;
        // let aulaJaExiste = verificaNomeAula(aula.nome);
        // if(aulaJaExiste) {
        //     alert("Aula já cadastrada!");
        //     return;
        // }
        let index = aulas.findIndex(e => e.id == aula.id);
        if(index === -1) {
            toastr.error("Erro interno ao alterar aula!");
            return;
        }
        $scope.aulas[index] = nAula;
        $scope.altAula = {};
        $scope.formAltAula.$setPristine();
        toastr.success("Aula alterada com sucesso!");
    };

    function verificaNomeAula(nome) {
        return $scope.aulas.some(e=>e.nome.toLowerCase().includes(nome.toLowerCase()));
    };
    
    $scope.removerAula = function(id) {
        var aulaTemVinculos = $scope.instrutores.some(e=>e.aula.includes(""+id));
        if(aulaTemVinculos) {
            toastr.warning("Não é possível excluir esta aula. Está sendo utilizada.");
            return;
        } else {
            var index = $scope.aulas.findIndex(e => e.id == id);
            if(index === -1) {
                toastr.error("Erro interno ao remover aula!");
                return;
            }
            $scope.aulas.splice(index, 1);
            $scope.removerAula.id = "";
            $scope.formExcAula.$setPristine();
            toastr.success("Aula removida com sucesso!");
        }
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