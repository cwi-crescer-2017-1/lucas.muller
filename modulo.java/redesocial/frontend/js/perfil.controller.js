angular.module('app')
.controller('PerfilCtrl', function($scope, $sce, $stateParams, $state, apiService, toastr) {
    $scope.id = $stateParams.id;
    $scope.dados = {};
    $scope.loading = false;
    $scope.loadingPosts = false;
    $scope.notFound = false;
    $scope.amigos = undefined;
    $scope.solicitacaoEnviada = false;
    $scope.enviandoSolicitacao = false;

    $scope.enviarSolicitacaoAmizade = function() {
        if($scope.enviandoSolicitacao)
            return;
        $scope.enviandoSolicitacao = true;
        apiService.novaAmizade($scope.id).then(() => {
            $scope.solicitacaoEnviada = true;
            $scope.enviandoSolicitacao = false;
        }, () => {
            toastr.error('Erro ao enviar solicitação de amizade');
            $scope.enviandoSolicitacao = false;
        });
    };

    $scope.getTextoFormatado = function(texto) {
        texto = markdown.toHTML(texto);
        texto = texto.replace(/(https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*))/g, `<a href="$1" target="_blank">$1</a>`);
        return $sce.trustAsHtml(texto);
    };

    $scope.removerPost = function(idpost) {
        if(confirm('Você tem certeza que quer remover este post?') == false)
            return;
            
        apiService.removerPost(idpost).then(() => {
            $scope.posts.splice($scope.posts.findIndex(el => el.id == idpost), 1);
            toastr.success('Post removido com sucesso');
        }, (response) => {
            toastr.error(response.data.message, 'Erro ao remover post');
        });
    };

    $scope.jaCurtiu = function(post) {
        return post.likes.some(el => el.idusuario == $scope.usuario.id);
    };

    $scope.contaCurtidas = function(post) {
        return post.likes == null ? 0 : post.likes.length;
    };

    let curtindo = false;
    $scope.curtir = function(post) {
        if(curtindo)
            return;
        curtindo = true;
        apiService.curtirPost(post.id).then(() => {
            curtindo = false;
            post.likes.push({idusuario: $scope.usuario.id});
            toastr.success('Post curtido');
        }, () => {
            curtindo = false;
            toastr.error('Erro ao curtir post');
        }); 
    };

    $scope.descurtir = function(post) {
        apiService.descurtirPost(post.id).then(() => {
            post.likes.splice(post.likes.findIndex(el => el.idusuario == $scope.usuario.id), 1);
            toastr.info('Post descurtido');
        }, () => {
            toastr.error('Erro ao descurtir post');
        }); 
    };

    function getPostsUsuario() {
        $scope.loadingPosts = true;
        apiService.getPostsUsuario($scope.id).then((response) => {
            $scope.loadingPosts = false;
            $scope.posts = response.data;
        }, () => {
            $scope.loadingPosts = false;
            toastr.error('Não foi possível obter os posts do usuário');
        });
    }

    function getUsuario() {
        $scope.loading = true;
        $scope.notFound = false;
        apiService.getUsuario($scope.id).then((response) => {
            $scope.dados = response.data;
            $state.current.data.pageTitle = response.data.nome;
            $scope.notFound = false;
            if($scope.usuario.id == $scope.id) {
                $scope.loading = false;
            } else {
                // verifica amizade
                apiService.getAmigos($scope.usuario.id).then((response) => {
                    $scope.amigos = response.data.some(usu => (usu.idusuario1.id == $scope.id|| usu.idusuario2.id == $scope.id) && usu.ativo == 1);
                    $scope.solicitacaoEnviada = response.data.some(usu => (usu.idusuario1.id == $scope.id || usu.idusuario2.id == $scope.id) && usu.ativo == 0);
                    $scope.loading = false;
                }, () => {
                    $scope.loading = false;
                });
            }
            getPostsUsuario();
        }, (response) => {
            $scope.loading = false;
            toastr.error(response.status == 404 ? 'Usuário não existe' : 'Erro ao obter dados do usuário');
            if(response.status == 404)
                $scope.notFound = true;
        });
    }
    getUsuario();
});