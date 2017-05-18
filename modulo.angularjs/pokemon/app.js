var app = angular.module('app', []);

app.controller('BuscaPokeCtrl',['$scope', '$http', function($s, $http){
    $s.buscaPokeByID = function(id) {
        $s.buscandoPokeByID = true;
        $.erroAoBuscarPokeByID = false;
        $http({
            method: 'GET',
            url: 'https://pokeapi.co/api/v2/pokemon/' + id
        }).then(function successCallback(response) {
            console.log(response.data);
            $s.pokemon = response.data;
            $s.buscandoPokeByID = false;
            $s.erroAoBuscarPokeByID = false;
        }, function errorCallback(response) {
            console.log(response);
            $s.buscandoPokeByID = false;
            $s.pokemon = null;
            $s.erroAoBuscarPokeByID = true;
        });
    };
}]);

app.filter('capitalize', function() {
    return function(input) {
      return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
    }
});

app.service('funcoes', function() {
    this.hexToRgb = function(hex) {
        // script retirado de http://stackoverflow.com/questions/5623838/rgb-to-hex-and-hex-to-rgb
        var shorthandRegex = /^#?([a-f\d])([a-f\d])([a-f\d])$/i;
        hex = hex.replace(shorthandRegex, function(m, r, g, b) {
            return r + r + g + g + b + b;
        });

        var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
        return result ? {
            r: parseInt(result[1], 16),
            g: parseInt(result[2], 16),
            b: parseInt(result[3], 16)
        } : null;
    }
});