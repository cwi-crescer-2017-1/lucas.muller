angular.module('app', ['ui.router', 'ngStorage', 'ui.gravatar', 'yaru22.angular-timeago', 'toastr', 'auth'])
.constant('siteName', 'Cube')
.constant('defaultRoute', '/login')
.constant('iconClass', 'cube')
.constant('colorClass', 'teal')
.constant('authConfig', {
    // Obrigatória - URL da API que retorna o usuário
    urlUsuario: 'http://localhost:9090/user',

    // Obrigatória - URL da aplicação que possui o formulário de login
    urlLogin: 'login',

    // Opcional - URL da aplicação para onde será redirecionado (se for informado) após o LOGIN com sucesso
    urlPrivado: 'feed',

    // Opcional - URL da aplicação para onde será redirecionado (se for informado) após o LOGOUT
    urlLogout: 'login'
})
.run(function($rootScope, $transitions, $state, $stateParams, toastr, colorClass, siteName, iconClass) {
    $rootScope.siteName = siteName;
    $rootScope.iconClass = iconClass;
    $rootScope.colorClass = colorClass;
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;
    $rootScope.showSpinner = false;
    $transitions.onStart({}, function() {
        $rootScope.showSpinner = true;
    });
    $transitions.onFinish({}, function() {
        $rootScope.showSpinner = false;
    });
    // $transitions.onError({}, function() {
    //     $rootScope.showSpinner = false;
    //     toastr.error(`Houve um erro ao carregar esta página`);
    // });
})
.config(function($stateProvider, $urlRouterProvider, defaultRoute, toastrConfig) {
    angular.extend(toastrConfig, {
        autoDismiss: true,
        containerId: 'toast-container',
        maxOpened: 2,    
        newestOnTop: true,
        positionClass: 'toast-top-right',
        preventDuplicates: false,
        preventOpenDuplicates: false,
        target: 'body'
    });

    $stateProvider
        // rotas externas
        .state('external', {
            templateUrl: 'templates/external/index.html',
            resolve: {
                checkUser: function(authService, authConfig, $state) {
                    if(authService.isAutenticado())
                        $state.go(authConfig.urlPrivado);
                    return true;
                }
            }
        }).state('login', {
            url: '/login',
            parent: 'external',
            templateUrl: 'templates/external/login.html',
            data : { pageTitle: 'Login' },
            controller: 'LoginCtrl'
        }).state('cadastro', {
            url: '/cadastro',
            parent: 'external',
            templateUrl: 'templates/external/cadastro.html',
            data : { pageTitle: 'Cadastro' },
            controller: 'CadastroCtrl'
        })
        .state('logout', {
            url: '/logout',
            controller: function(authService, toastr) {
                authService.logout();
                toastr.info('Você foi deslogado', 'Informação');
            },
            data : { pageTitle: 'Logout' }
        })
        // rotas internas
        .state('internal', {
            templateUrl: 'templates/internal/index.html',
            resolve: {
                checkLogin: function(authService) {
                    return authService.isAutenticadoPromise();
                }
            },
            controller: function($scope, $state, $localStorage) {
                $scope.usuario = $localStorage.usuarioLogado;
                $scope.pesquisar = function(termo) {
                    if(termo != null && termo.trim().length > 0)
                        $state.go('busca', {termo: termo});
                }
            }
        }).state('feed', {
            url: '/feed',
            parent: 'internal',
            templateUrl: 'templates/internal/feed.html',
            data : { pageTitle: 'Feed' },
            controller: 'FeedCtrl',
            resolve: {
                checkLogin: function(authService) {
                    return authService.isAutenticadoPromise();
                }
            }
        }).state('user', {
            url: '/user/{id:int}',
            parent: 'internal',
            data : { pageTitle: 'Usuário' },
            templateUrl: 'templates/internal/perfil.html',
            controller: 'PerfilCtrl',
            resolve: {
                checkLogin: function(authService) {
                    return authService.isAutenticadoPromise();
                }
            }
        }).state('conta', {
            url: '/conta',
            parent: 'internal',
            data : { pageTitle: 'Sua conta' },
            templateUrl: 'templates/internal/conta.html',
            controller: 'ContaCtrl',
            resolve: {
                checkLogin: function(authService) {
                    return authService.isAutenticadoPromise();
                }
            }
        }).state('amigos', {
            url: '/amigos',
            parent: 'internal',
            data : { pageTitle: 'Seus amigos' },
            templateUrl: 'templates/internal/amigos.html',
            controller: 'AmigosCtrl',
            resolve: {
                checkLogin: function(authService) {
                    return authService.isAutenticadoPromise();
                }
            }
        }).state('busca', {
            url: '/busca/{termo}',
            parent: 'internal',
            data : { pageTitle: 'Busca' },
            templateUrl: 'templates/internal/busca.html',
            controller: 'BuscaCtrl',
            resolve: {
                checkLogin: function(authService) {
                    return authService.isAutenticadoPromise();
                }
            }
        });
    $urlRouterProvider.otherwise(defaultRoute);
})
.directive('script', function() {
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