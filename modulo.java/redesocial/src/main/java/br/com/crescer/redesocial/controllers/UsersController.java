/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.controllers;

import br.com.crescer.redesocial.entidades.Post;
import br.com.crescer.redesocial.entidades.Usuario;
import br.com.crescer.redesocial.entidades.UsuarioAmizade;
import br.com.crescer.redesocial.services.PostsService;
import br.com.crescer.redesocial.services.UsersService;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author lucas.muller
 */
@RestController
@RequestMapping(value="/user")
public class UsersController {
    
    @Autowired
    UsersService service;
    
    @Autowired
    PostsService postsService;
    
    @Autowired
    UsuarioLogado usuarioLogado;
    
    @GetMapping
    public Usuario getUserDetails() {
        return usuarioLogado.getUsuarioLogado();
    }
    
    @GetMapping("/{id}")
    public Usuario getUserByID(@PathVariable BigDecimal id) {
        return service.findByID(id);
    }
    
    @GetMapping("/search")
    public Page<Usuario> findBySearch(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit, @RequestParam String termo) {
        return service.findBySearch(page, limit, termo);
    }
    
    @GetMapping("/posts")
    public Collection<Post> getUserPosts() {
        return usuarioLogado.getUsuarioLogado().getPostCollection();
    }
    
    @GetMapping("/amigos")
    public Collection<UsuarioAmizade> getUserAmigos() {
        Set<UsuarioAmizade> amigos = new HashSet();
        amigos.addAll(usuarioLogado.getUsuarioLogado().getUsuarioAmizadeCollection());
        amigos.addAll(usuarioLogado.getUsuarioLogado().getUsuarioAmizadeCollection1());
        return amigos;
    }
    
    @GetMapping("/{id}/posts")
    public Iterable<Post> findByUser(@PathVariable BigDecimal id) {
        return service.findByID(id).getPostCollection();
    }
    
    @PutMapping
    public Usuario update(@RequestBody Usuario entidade) {
        BigDecimal id = usuarioLogado.getUsuarioLogado().getId();
        entidade.setId(id);
        return service.update(id, entidade);
    }
    
    @PostMapping
    public Usuario save(@RequestBody Usuario entidade) {
        if(usuarioLogado.getUsuarioLogado() != null)
            throw new RuntimeException("Você já está logado");
        return service.save(entidade);
    }
    
}
