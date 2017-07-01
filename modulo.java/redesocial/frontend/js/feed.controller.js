angular.module('app')
.controller('FeedCtrl', function($scope, authService, toastr, apiService) {
    $scope.usuario = authService.getUsuario();
    $scope.posts = [];
    $scope.page = 0;
    $scope.limit = 10;
    $scope.lastPage = false;
    $scope.loadingPosts = false;
    $scope.postando = false;
    $scope.addPost = function(post) {
        $scope.postando = true;
        apiService.addPost(post).then((response) => {
            $scope.postando = false;
            $scope.post.texto = "";
            $scope.posts.unshift(response.data);
            toastr.success('Post publicado com sucesso');
        }, () => {
            $scope.postando = false;
            toastr.error('Erro ao adicionar post');
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

    $scope.getPosts = getPosts;
    $scope.getNextPage = getNextPage;
    getPosts($scope.page, $scope.limit);
    
    function getPosts(page, limit) {
        $scope.loadingPosts = true;
        apiService.getPosts(page, limit).then((response)=>{
            $scope.posts = $scope.posts.concat(response.data.content);

            $scope.loadingPosts = false;
            $scope.lastPage = response.data.last;
        }, (response) => {
            $scope.loadingPosts = false;
            toastr.error('Verifique sua conexão', 'Erro ao obter posts');
        });
    }

    function getNextPage() {
        getPosts(++$scope.page, $scope.limit);
    }
});