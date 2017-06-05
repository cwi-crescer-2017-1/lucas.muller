var app = angular.module('EditoraCrescer', ['ui.router', 'auth', 'toastr']);

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

    var administrativoState = {
        name: 'adm-index',
        url: '/administrativo/home',
        templateUrl: 'templates/adm/index.html',
        controller: 'AdmCtrl',
        resolve: {
            verifica: function(authService) {
                return authService.isAutenticadoPromise();
            },
            getNaoRevisados: function(LivrosFactory) {
                return LivrosFactory.ObterNaoRevisados();
            }
        }
    };

    var loginState = {
        name: 'adm-login',
        url: '/administrativo/login',
        templateUrl: 'templates/adm/login.html',
        controller: 'LoginCtrl'
    };

    var novoAutorState = {
        name: 'adm-autor-novo',
        url: '/administrativo/autores/novo',
        templateUrl: 'templates/adm/novoAutor.html',
        controller: 'NovoAutorCtrl',
        resolve: {
            verifica: function($state, authService, toastr) {
                if(!(authService.possuiPermissao('Administrador') || authService.possuiPermissao('Publicador'))) {
                    toastr.info('Você não possui permissão!');
                    $state.go('adm-index');
                }
                return null;
            }
        }
    };

    var novoLivroState = {
        name: 'adm-livro-novo',
        url: '/administrativo/livros/novo',
        templateUrl: 'templates/adm/novoLivro.html',
        controller: 'NovoLivroCtrl',
        resolve: {
            verifica: function($state, authService, toastr) {
                if(!(authService.possuiPermissao('Administrador') || authService.possuiPermissao('Publicador'))) {
                    toastr.info('Você não possui permissão!');
                    $state.go('adm-index');
                }
                return null;
            },
            obterAutores: function(AutoresFactory) {
                return AutoresFactory.ObterAutores();
            }
        }
    };

    var excluirLivroState = {
        name: 'adm-livro-excluir',
        url: '/administrativo/livros/:id/excluir',
        templateUrl: 'templates/adm/excluirLivro.html',
        controller: 'ExcluirLivroCtrl',
        resolve: {
            verifica: function($state, authService, toastr) {
                if(!(authService.possuiPermissao('Administrador') || authService.possuiPermissao('Publicador'))) {
                    toastr.info('Você não possui permissão!');
                    $state.go('adm-index');
                }
                return null;
            },
            obterLivro: function(LivrosFactory, $stateParams) {
                return LivrosFactory.ObterLivro($stateParams.id);
            }
        }
    };

    var revisarLivroState = {
        name: 'adm-livro-revisar',
        url: '/administrativo/livros/:id/revisar',
        templateUrl: 'templates/adm/revisarLivro.html',
        controller: 'RevisarLivroCtrl',
        resolve: {
            verifica: function($state, authService, toastr) {
                if(!(authService.possuiPermissao('Administrador') || authService.possuiPermissao('Revisor'))) {
                    toastr.info('Você não possui permissão!');
                    $state.go('adm-index');
                }
                return null;
            },
            obterLivro: function(LivrosFactory, $stateParams) {
                return LivrosFactory.ObterLivro($stateParams.id);
            }
        }
    };

    var publicarLivroState = {
        name: 'adm-livro-publicar',
        url: '/administrativo/livros/:id/publicar',
        templateUrl: 'templates/adm/publicarLivro.html',
        controller: 'PublicarLivroCtrl',
        resolve: {
            verifica: function($state, authService, toastr) {
                if(!(authService.possuiPermissao('Administrador') || authService.possuiPermissao('Publicador'))) {
                    toastr.info('Você não possui permissão!');
                    $state.go('adm-index');
                }
                return null;
            },
            obterLivro: function(LivrosFactory, $stateParams) {
                return LivrosFactory.ObterLivro($stateParams.id);
            }
        }
    };

    var editarLivroState = {
        name: 'adm-livro-editar',
        url: '/administrativo/livros/:id',
        templateUrl: 'templates/adm/editarLivro.html',
        controller: 'EditarLivroCtrl',
        resolve: {
            verifica: function($state, authService, toastr) {
                if(!(authService.possuiPermissao('Administrador') || authService.possuiPermissao('Publicador'))) {
                    toastr.info('Você não possui permissão!');
                    $state.go('adm-index');
                }
                return null;
            },
            obterLivro: function(LivrosFactory, $stateParams) {
                return LivrosFactory.ObterLivro($stateParams.id);
            },
            obterAutores: function(AutoresFactory) {
                return AutoresFactory.ObterAutores();
            }
        }
    };

    $stateProvider.state(indexState);
    $stateProvider.state(livroState);
    $stateProvider.state(administrativoState);
    $stateProvider.state(loginState);
    $stateProvider.state(novoLivroState);
    $stateProvider.state(excluirLivroState);
    $stateProvider.state(editarLivroState);
    $stateProvider.state(revisarLivroState);
    $stateProvider.state(publicarLivroState);
    $stateProvider.state(novoAutorState);
    $urlRouterProvider.otherwise('/index/1');
});

app.run(function($rootScope) {
    $rootScope.siteName = 'Editora Crescer';
});

app.constant('APIBaseURL', 'http://localhost:62422/api');

app.constant('authConfig', {

    // Obrigatória - URL da API que retorna o usuário
    urlUsuario: 'http://localhost:62422/api/acessos/usuario',

    // Obrigatória - URL da aplicação que possui o formulário de login
    urlLogin: 'adm-login',

    // Opcional - URL da aplicação para onde será redirecionado (se for informado) após o LOGIN com sucesso
    urlPrivado: 'adm-index',

    // Opcional - URL da aplicação para onde será redirecionado (se for informado) após o LOGOUT
    urlLogout: 'index'
});

app.factory('LivrosFactory', function($http, APIBaseURL) {
    return {
        ObterLancamentos: () => $http.get(`${APIBaseURL}/livros/lancamentos?limit=6`),
        ObterLivros: (limit, page) => $http.get(`${APIBaseURL}/livros?limit=${limit}&page=${page}`),
        ObterLivro: (id) => $http.get(`${APIBaseURL}/livros/${id}`),
        CadastrarLivro: (livro) => $http.post(`${APIBaseURL}/livros`, livro),
        ExcluirLivro: (id) => $http.delete(`${APIBaseURL}/livros/${id}`),
        EditarLivro: (id, livro) => $http.put(`${APIBaseURL}/livros/${id}`, livro),
        RevisarLivro: (id) => $http.get(`${APIBaseURL}/livros/${id}/revisar`),
        PublicarLivro: (id) => $http.get(`${APIBaseURL}/livros/${id}/publicar`),
        ObterNaoRevisados: () => $http.get(`${APIBaseURL}/livros/naorevisados`)
    };
});

app.factory('AutoresFactory', function($http, APIBaseURL) {
    return {
        ObterAutores: () => $http.get(`${APIBaseURL}/autores`),
        ObterAutor: (id) => $http.get(`${APIBaseURL}/autores/${id}`),
        CadastrarAutor: (autor) => $http.post(`${APIBaseURL}/autores`, autor),
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

app.controller('LivroCtrl', function($scope, $stateParams, authService, LivrosFactory) {
    $scope.erroAoObterLivro = false;
    $scope.livroEncontrado = true;
    $scope.livro = null;
    $scope.id = $stateParams.id;
    $scope.auth = authService;
    ObterLivro();

    function ObterLivro() {
        $scope.erroAoObterLivro = false;
        $scope.livroEncontrado = true;

        LivrosFactory.ObterLivro($stateParams.id)
            .then(response => {
                console.log(response.data);
                $scope.livro = response.data;
                $scope.erroAoObterLivro = false;
                $scope.livroEncontrado = true;
            }, (response) => {
                $scope.erroAoObterLivro = true;
                if(response.status == 404) $scope.livroEncontrado = false;
            });
    }
});

app.controller('LoginCtrl', function(authService, authConfig, $state, $scope, toastr) {
    if(authService.isAutenticado())
        $state.go(authConfig.urlPrivado);

    $scope.erroAoLogar = false;
    $scope.logar = function(usuario) {
        toastr.info('Por favor, aguarde...', 'Fazendo login');
        $scope.erroAoLogar = false;
        authService.login(usuario).then((response) => {
            console.log(response.data);
            $scope.erroAoLogar = false;
            toastr.success('Você fez login com sucesso.', `Bem-vindo ${response.data.Nome}!`);
        }, (response) => {
            $scope.erroAoLogar = true;
            toastr.error(response.status==401?'Verifique seus dados.':'Verifique o servidor.', 'Erro ao fazer login');
        });
    };
});

app.controller('AdmCtrl', function($scope, authService, toastr, getNaoRevisados) {
    $scope.user = authService.getUsuario();
    $scope.logout = function(usuario) {
        authService.logout(usuario);
        toastr.info('Você fez logout com sucesso!');
    };
    $scope.naorevisados = getNaoRevisados.data;
    $scope.auth = authService;
});

app.controller('NovoAutorCtrl', function($scope, AutoresFactory, toastr, $state) {
    $scope.cadastrar = function(autor) {
        toastr.info('Por favor, aguarde...', 'Cadastrando autor');
        AutoresFactory.CadastrarAutor(autor).then((response) => {
            toastr.success('Autor cadastrado com sucesso!');
            console.log(response.data);
            $scope.novoAutor = [];
            $state.go('adm-index');
        }, (response) => {
            toastr.error(response.status==401? 'Você não tem permissão.':response.status==500?'Erro no servidor.':'Verifique seus dados e conexão.', 'Erro ao cadastrar');
        });
    }
});

app.controller('NovoLivroCtrl', function($scope, LivrosFactory, toastr, $state, obterAutores) {
    $scope.autores = obterAutores.data;

    $scope.cadastrar = function(livro) {
        toastr.info('Por favor, aguarde...', 'Cadastrando livro');
        livro.DataPublicao = null;
        livro.IdRevisor = null;
        livro.DataRevisao = null;
        console.log(livro);
        LivrosFactory.CadastrarLivro(livro).then((response) => {
            toastr.success('Livro cadastrado com sucesso!');
            console.log(response.data);
            $scope.novoLivro = [];
            $state.go('livro', {id: response.data.Isbn});
        }, (response) => {
            toastr.error(response.status==401? 'Você não tem permissão.':response.status==500?'Erro no servidor.':'Verifique seus dados e conexão.', 'Erro ao cadastrar');
        });
    }
});

app.controller('ExcluirLivroCtrl', function($scope, obterLivro, toastr, $state, LivrosFactory) {
    $scope.livro = obterLivro.data;
    $scope.excluir = function(isbn) {
        LivrosFactory.ExcluirLivro(isbn).then(() => {
            toastr.info('Livro excluído!');
            $state.go('adm-index');
        }, () => {
            toastr.error('Erro ao excluir livro.');
            $state.go('adm-index');
        });
    };
});

app.controller('EditarLivroCtrl', function($scope, obterLivro, obterAutores, $stateParams, toastr, $state, LivrosFactory) {
    $scope.livro = obterLivro.data;
    $scope.autores = obterAutores.data;

    $scope.editar = function(livro) {
        console.log(livro);
        livro.IdAutor = livro.Autor.Id;
        LivrosFactory.EditarLivro($stateParams.id, livro).then((response) => {
            toastr.success('Livro editado com sucesso');
            $state.go('livro', {id: response.data.Isbn});
        }, () => {
            toastr.error('Erro ao editar livro.');
        });
    };
});

app.controller('RevisarLivroCtrl', function($scope, obterLivro, LivrosFactory, toastr, $state) {
    $scope.livro = obterLivro.data;
    $scope.revisar = function(isbn) {
        LivrosFactory.RevisarLivro(isbn).then((response) => {
            toastr.success('Livro revisado com sucesso!');
            console.log(response.data);
            $state.go('livro', {id: isbn});
        }, (response) => {
            toastr.error(response.status==401? 'Você não tem permissão.':response.status==500?'Erro no servidor.':'Verifique seus dados e conexão.', 'Erro ao revisar livro');
        });
    };
});
app.controller('PublicarLivroCtrl', function($scope, obterLivro, LivrosFactory, toastr, $state) {
    $scope.livro = obterLivro.data;
    $scope.publicar = function(isbn) {
        LivrosFactory.PublicarLivro(isbn).then((response) => {
            toastr.success('Livro publicado com sucesso!');
            console.log(response.data);
            $state.go('livro', {id: isbn});
        }, (response) => {
            toastr.error(response.status==401? 'Você não tem permissão.':response.status==500?'Erro no servidor.':'Verifique seus dados e conexão.', 'Erro ao publicar livro');
        });
    };
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

  app.directive('showDuringResolve', function($rootScope) {

  return {
    link: function(scope, element) {

      element.addClass('ng-hide');

      var unregister = $rootScope.$on('$routeChangeStart', function() {
        element.removeClass('ng-hide');
      });

      scope.$on('$destroy', unregister);
    }
  };
});