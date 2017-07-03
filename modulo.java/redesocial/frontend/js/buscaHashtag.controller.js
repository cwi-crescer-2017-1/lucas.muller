angular.module('app')
.controller('BuscaHashtagCtrl', function($scope, $stateParams, toastr, apiService) {
    $scope.hashtag = $stateParams.hashtag;
    $scope.posts = [];
    $scope.loadingPosts = false;
    $scope.page = 0;
    $scope.limit = 10;
    $scope.getNextPage = getNextPage;

    function buscar(page, limit) {
        $scope.loadingPosts = true;
        apiService.buscaPosts(page, limit, $scope.hashtag.replace(/#/g, `%23`)).then((response) => {
            $scope.posts = response.data.content;
            $scope.loadingPosts = false;
            $scope.lastPage = response.data.last;
        }, () => {
            toastr.error('Erro ao buscar posts');
            $scope.loadingPosts = false;
        });
    }
    buscar($scope.page, $scope.limit);

    function getNextPage() {
        getPosts(++$scope.page, $scope.limit);
    }
});