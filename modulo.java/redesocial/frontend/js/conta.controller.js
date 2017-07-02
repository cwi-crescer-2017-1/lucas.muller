angular.module('app')
.controller('ContaCtrl', function($scope, $localStorage, authService, apiService, toastr) {
    $scope.usuario = angular.copy($scope.usuario);
    if($scope.usuario.dataNascimento != null)
        $scope.usuario.dataNascimento = new Date($scope.usuario.dataNascimento);
    $scope.alterar = function(usuario) {
        apiService.alterarUsuario(usuario).then(() => {
            $localStorage.usuarioLogado = $scope.usuario;
            if($scope.alterarSenha == true) {
                $localStorage.usuarioLogado.senha = null;
                authService.logout();
                toastr.success('Faça login com a nova senha', 'Alteração bem sucedida');
            } else {
                toastr.success('Talvez seja necessário fazer login novamente para que as modificações façam efeito', 'Alteração bem sucedida');
            }
        }, () => {
            toastr.error('Erro ao alterar sua conta');
        });
    };
});