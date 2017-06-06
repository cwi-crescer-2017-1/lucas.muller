angular.module('app')
.controller('IndexCtrl', function(authService, $scope) {
    $scope.usuario = authService.getUsuario();
});