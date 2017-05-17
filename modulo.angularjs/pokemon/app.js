var app = angular.module('app', []);

app.controller('BuscaPokeCtrl',['$scope', '$http', function($s, $http){
    $s.buscaPokeByID = function(id) {
        $s.buscandoPokeByID = true;
        $http({
            method: 'GET',
            url: 'https://pokeapi.co/api/v2/pokemon/' + id
        }).then(function successCallback(response) {
            console.log(response.data);
            $s.pokemon = response.data;
            $s.buscandoPokeByID = false;
        }, function errorCallback(response) {
            console.log(response);
            $s.buscandoPokeByID = false;
        });
    };
}]);

app.filter('capitalize', function() {
    return function(input) {
      return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
    }
});