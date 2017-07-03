angular.module('app')
.controller('FeedCtrl', function($scope, $filter, authService, toastr, apiService) {
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