var app = angular.module('app', []);

app.controller('Ex01Ctrl', ['$scope', '$filter', function($s, $filter) {
    $s.converterData = function(data) {
        data = data.split('/');
        data = `${data[1]}/${data[0]}/${data[2]}`;
        $s.dataFormatada = $filter('date')(new Date(data), 'mediumDate');
    };
}]);