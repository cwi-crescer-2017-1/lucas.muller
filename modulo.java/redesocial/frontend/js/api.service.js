angular.module('app')
.constant('apiBaseUrl', 'http://localhost:9090')
.service('apiService', function($http, authService, apiBaseUrl) {
    return {
        cadastrarUsuario: (usuario) => $http.post(`${apiBaseUrl}/user`, usuario),
        alterarUsuario: (usuario) => $http.put(`${apiBaseUrl}/user`, usuario),
        getUsuario: (idusuario) => $http.get(`${apiBaseUrl}/user/${idusuario}`),
        getPostsUsuario: (idusuario) => $http.get(`${apiBaseUrl}/user/${idusuario}/posts`),
        // getAmigosUsuario: (usuario) => $http.get(`${apiBaseUrl}/user/${usuario}/amigos`),
        buscarUsuario: (termo) => $http.get(`${apiBaseUrl}/user/search?termo=${termo}`),
        buscarUsuario: (termo, page, limit) => $http.get(`${apiBaseUrl}/user/search?termo=${termo}&page=${page}&limit=${limit}`),
        getAmigos: () => $http.get(`${apiBaseUrl}/user/amigos`),
        addPost: (post) => $http.post(`${apiBaseUrl}/posts`, post),
        getPosts: (page, limit) => $http.get(`${apiBaseUrl}/posts?page=${page}&limit=${limit}`),
        removerPost: (idpost) => $http.delete(`${apiBaseUrl}/posts/${idpost}`),
        curtirPost: (idpost) => $http.get(`${apiBaseUrl}/posts/${idpost}/curtir`),
        descurtirPost: (idpost) => $http.get(`${apiBaseUrl}/posts/${idpost}/descurtir`),
        aceitarAmizade: (idamizade) => $http.get(`${apiBaseUrl}/amizades/${idamizade}/aceitar`),
        rejeitarAmizade: (idamizade) => $http.delete(`${apiBaseUrl}/amizades/${idamizade}`),
        novaAmizade: (idamigo) => {
            let obj = {
                idusuario1: { id: authService.getUsuario().id },
                idusuario2: { id: idamigo }
            };
            return $http.post(`${apiBaseUrl}/amizades`, obj);
        }
    };
});