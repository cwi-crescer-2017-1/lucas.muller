var app = angular.module('app', []);

app.run(function() {
    console.log('App carregado com sucesso!');
});

app.controller('Ex01Ctrl', ['$scope', '$filter', function($s, $filter) {
    $s.converterData = function(data) {
        data = data.replace(/([0-9]{2})[- /.]([0-9]{2})[- /.]([0-9]{4})/ig, '$2/$1/$3');
        $s.dataFormatada = $filter('date')(new Date(data), 'mediumDate');
    };
}]);

app.controller('InstrutoresCtrl', ['$scope', function($s){
    $s.instrutores = [{
        nome: 'Bernardo',
        aula: [{
            numero: 1,
            nome: 'OO'
        },
        {
            numero: 4,
            nome: 'Javascript'
        }
        ]
    },
    {
        nome: 'Nunes',
        aula: [{
        numero: 2,
        nome: 'Banco de Dados I'
        }]
    },
    {
        nome: 'Pedro (PHP)',
        aula: [{
        numero: 3,
        nome: 'HTML e CSS'
        }]
    },
    {
        nome: 'Zanatta',
        aula: [{
        numero: 5,
        nome: 'AngularJS'
        }]
    }
    ];

    $s.arrayAulas = transformarArrayInstrutores($s.instrutores);

    function transformarArrayInstrutores(array) {
        var novoArray = [];
        angular.forEach(array, function(instrutor) {
            angular.forEach(instrutor.aula, function(aula) {
                novoArray.push({numero: aula.numero, nome: aula.nome, instrutor: instrutor.nome});
            });
        });
        return novoArray;
    };
}]);

app.controller('Ex02Ctrl', ['$scope', function($s){  }]);

app.controller('Ex03Ctrl', ['$scope', function($s){  }]);

app.filter('mascada', function() {
    return function(valor) {
        return valor.replace(/(nunes)/ig, '$ $1 $');
    };
});

app.filter('formataAula', function() {
    return function(aula) {
        var str = "" + aula.numero;
        var numeroFormatado = "000".substring(0, 3 - str.length) + str;
        return `${numeroFormatado} - ${aula.nome.toUpperCase()}`;
    };
});