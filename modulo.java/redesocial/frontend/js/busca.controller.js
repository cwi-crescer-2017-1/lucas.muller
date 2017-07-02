angular.module('app')
.controller('BuscaCtrl', function($scope, $stateParams, apiService, toastr) {
    $scope.termo = $stateParams.termo;
    $scope.usuarios = [];
    $scope.page = 0;
    $scope.limit = 10;
    $scope.lastPage = false;
    $scope.loading = false;
    $scope.buscarMais = buscar;

    function buscar() {
        $scope.loading = true;
        apiService.buscarUsuario($scope.termo, $scope.page++, $scope.limit).then((response) => {
            var data = response.data;
            var content = data.content;
            $scope.loading = false;
            $scope.lastPage = data.last;
            var indexUsuarioAtual = content.findIndex(u => u.id == $scope.usuario.id);
            if(indexUsuarioAtual > -1)
                content.splice(indexUsuarioAtual, 1);
            $scope.usuarios = $scope.usuarios.concat(content);
        }, () => {
            $scope.loading = false;
            toastr.error('Erro ao efetuar busca');
        });
    }
    buscar();
});