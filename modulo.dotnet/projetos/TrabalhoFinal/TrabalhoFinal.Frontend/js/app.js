angular.module('app', ['auth', 'ui.router', 'toastr'])
.run(function($rootScope) {
    $rootScope.siteName = "Locações Crescer";
})
.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider
    .state('login', {
        url: '/login',
        templateUrl: 'templates/login.html',
        controller: 'LoginCtrl',
        resolve: {
            verificaLogin: function(authService, $state) {
                if(authService.isAutenticado())
                    $state.go('index');
            }
        }
    })
    .state('logout', {
        url: '/logout',
        resolve: {
            efetuarLogout: function(authService, toastr) {
                authService.logout();
                toastr.info('Você foi deslogado.');
            }
        }
    })
    .state('index', {
        url: '/index',
        templateUrl: 'templates/index.html',
        controller: 'IndexCtrl',
        resolve: {
            verificaLogin: function(authService) {
                return authService.isAutenticadoPromise();
            }
        }
    });
    $urlRouterProvider.otherwise('/login');
})
.constant('authConfig', {
    urlUsuario: 'http://localhost:64885/api/acessos/usuario',
    urlLogin: 'login',
    urlPrivado: 'index',
    urlLogout: 'login'
});