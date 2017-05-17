var app = angular.module('app', []);

app.controller('Ex01', function($scope) {
    $scope.pokemon = { nome: "Bulbassauro", vida: 100};
});

app.controller('Ex02', function($scope) {
    $scope.pokemons = [
        { nome: "Bulbassauro", tipo: "Grama e Venenoso"},
        { nome: "Charmander", tipo: "Fogo"},
        { nome: "Dragonite", tipo: "Dragão e Voador"},
        { nome: "Caterpie", tipo: "Inseto"},
        { nome: "Voltorb", tipo: "Elétrico"}
    ];
});