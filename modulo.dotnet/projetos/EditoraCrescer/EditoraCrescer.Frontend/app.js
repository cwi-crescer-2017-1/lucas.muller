var app = angular.module('EditoraCrescer', ['ui.router']);

app.config(function($stateProvider, $urlRouterProvider) {
    var indexState = {
        name: 'index',
        url: '/index/:pagina',
        templateUrl: 'templates/index.html',
        controller: 'InicioCtrl'
    };

    var livroState = {
        name: 'livro',
        url: '/livro/:id',
        templateUrl: 'templates/livro.html',
        controller: 'LivroCtrl'
    };

    $stateProvider.state(indexState);
    $stateProvider.state(livroState);
    $urlRouterProvider.otherwise('/index/');
});

app.run(function($rootScope) {
    $rootScope.siteName = 'Editora Crescer';
});

app.constant('APIBaseURL', 'http://localhost:62422/api');

app.factory('LivrosFactory', function($http, APIBaseURL) {
    return {
        ObterLancamentos: ObterLancamentos,
        ObterLivros: ObterLivros,
        ObterLivro: (id) => $http.get(`${APIBaseURL}/livros/${id}`)
    };

    function ObterLancamentos() {
        return $http.get(`${APIBaseURL}/livros/lancamentos?limit=6`);
    };

    function ObterLivros(limit, page) {
        return $http.get(`${APIBaseURL}/livros?limit=${limit}&page=${page}`);
    };
});

app.controller('MainCtrl', function($scope, LivrosFactory) {
    // inicia variáveis do escopo
    $scope.erroAoObterLancamentos = false;
    $scope.lancamentos = null;
    
    // inicia métodos
    ObterLancamentos();

    // métodos
    function ObterLancamentos() {
        $scope.erroAoObterLancamentos = false;

        LivrosFactory.ObterLancamentos().then(response => {
            $scope.lancamentos = response.data;
            $scope.erroAoObterLancamentos = false;
        }, () => {
            $scope.erroAoObterLancamentos = true;
        });
    };
});

app.controller('InicioCtrl', function($scope, $stateParams, LivrosFactory) {
    $scope.erroAoObterLivros = false;
    $scope.pagina = parseInt($stateParams.pagina) || 1;
    $scope.limite = 6;
    $scope.ultimaPagina = false;
    $scope.livros = null;

    ObterLivros();

    function ObterLivros() {
        $scope.erroAoObterLivros = false;

        LivrosFactory.ObterLivros($scope.limite, $scope.pagina)
            .then(response => {
                $scope.livros = response.data.Livros;
                $scope.ultimaPagina = response.data.UltimaPagina;
                $scope.erroAoObterLivros = false;
            }, () => {
                $scope.erroAoObterLivros = true;
            });
    }
});

app.controller('LivroCtrl', function($scope, $stateParams, LivrosFactory) {
    $scope.erroAoObterLivro = false;
    $scope.livroEncontrado = true;
    $scope.livro = null;
    $scope.id = $stateParams.id;

    ObterLivro();

    function ObterLivro() {
        $scope.erroAoObterLivro = false;
        $scope.livroEncontrado = true;

        LivrosFactory.ObterLivro($stateParams.id)
            .then(response => {
                $scope.livro = response.data;
                $scope.erroAoObterLivro = false;
                $scope.livroEncontrado = true;
            }, (response) => {
                $scope.erroAoObterLivro = true;
                if(response.status == 404) $scope.livroEncontrado = false;
            });
    }
});

app.directive('loader', function() {
  return {
    restrict: 'E',
    templateUrl: 'templates/loader.html'
  };
});

app.directive('script', function() {
    return {
      restrict: 'E',
      scope: false,
      link: function(scope, elem, attr) {
        if (attr.type === 'text/javascript-lazy') {
          var code = elem.text();
          var f = new Function(code);
          f();
        }
      }
    };
  });