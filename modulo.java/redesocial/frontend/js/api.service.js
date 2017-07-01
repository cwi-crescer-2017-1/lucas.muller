angular.module('app')
.constant('apiBaseUrl', 'http://localhost:9090')
.service('apiService', function($http, apiBaseUrl) {
    return {
        cadastrarUsuario: (usuario) => $http.post(`${apiBaseUrl}/user`, usuario),
        alterarUsuario: (usuario) => $http.put(`${apiBaseUrl}/user`, usuario),
        getAmigos: () => $http.get(`${apiBaseUrl}/user/amigos`),
        addPost: (post) => $http.post(`${apiBaseUrl}/posts`, post),
        getPosts: (page, limit) => $http.get(`${apiBaseUrl}/posts?page=${page}&limit=${limit}`),
        curtirPost: (idpost) => $http.get(`${apiBaseUrl}/posts/${idpost}/curtir`),
        descurtirPost: (idpost) => $http.get(`${apiBaseUrl}/posts/${idpost}/descurtir`),
        aceitarAmizade: (idamizade) => $http.get(`${apiBaseUrl}/amizades/${idamizade}/aceitar`),
        rejeitarAmizade: (idamizade) => $http.delete(`${apiBaseUrl}/amizades/${idamizade}`),
    };
});