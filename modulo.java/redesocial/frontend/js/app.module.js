angular.module('app', ['ui.router', 'toastr', 'auth'])
.constant('siteName', 'Cube')
.constant('defaultRoute', '/login')
.constant('iconClass', 'cube')
.constant('authConfig', {
    // Obrigatória - URL da API que retorna o usuário
    urlUsuario: 'http://localhost:9090/user',

    // Obrigatória - URL da aplicação que possui o formulário de login
    urlLogin: '/login',

    // Opcional - URL da aplicação para onde será redirecionado (se for informado) após o LOGIN com sucesso
    urlPrivado: '',

    // Opcional - URL da aplicação para onde será redirecionado (se for informado) após o LOGOUT
    urlLogout: '/login'
})
.run(function($rootScope, $transitions, $state, $stateParams, siteName, iconClass) {
    $rootScope.siteName = siteName;
    $rootScope.iconClass = iconClass;
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;
    $rootScope.showSpinner = false;
    $transitions.onStart({}, function() {
        $rootScope.showSpinner = true;
    });
    $transitions.onFinish({}, function() {
        $rootScope.showSpinner = false;
    });
})
.config(function($stateProvider, $urlRouterProvider, defaultRoute, toastrConfig) {
    angular.extend(toastrConfig, {
        autoDismiss: true,
        containerId: 'toast-container',
        maxOpened: 2,    
        newestOnTop: true,
        positionClass: 'toast-top-right',
        preventDuplicates: true,
        preventOpenDuplicates: false,
        target: 'body'
    });

    $stateProvider.state('external', {
        templateUrl: 'templates/external/index.html',
        resolve: {
            verificaLogin: function(authService, authConfig, $state) {
                if(authService.isAutenticado())
                    $state.go(authConfig.urlPrivado);
            }
        }
    }).state('login', {
        url: '/login',
        parent: 'external',
        templateUrl: 'templates/external/login.html',
        data : { pageTitle: 'Login' }
    }).state('cadastro', {
        url: '/cadastro',
        parent: 'external',
        templateUrl: 'templates/external/cadastro.html',
        data : { pageTitle: 'Cadastro' }
    });
    $urlRouterProvider.otherwise(defaultRoute);
});