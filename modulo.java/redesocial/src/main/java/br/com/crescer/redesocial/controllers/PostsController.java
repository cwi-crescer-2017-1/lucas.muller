/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.controllers;

import br.com.crescer.redesocial.entidades.Post;
import br.com.crescer.redesocial.services.LikesService;
import br.com.crescer.redesocial.services.PostsService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author lucas.muller
 */
@RestController
@RequestMapping(value="/posts")
public class PostsController {
    
    @Autowired
    private PostsService service;
    
    @Autowired
    private LikesService likesService;
    
    @Autowired
    UsuarioLogado usuarioLogado;
    
    @GetMapping
    public Page<Post> getFeed(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        return service.getFeed(page, limit);
    }
    
    @GetMapping("/search")
    public Page<Post> findPostsContaining(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit, @RequestParam String texto) {
        return service.findPostsContaining(page, limit, texto);
    }
    
    @GetMapping("/{id}")
    public Post findByID(@PathVariable BigDecimal id) {
        return service.findByID(id);
    }
    
    @GetMapping("/{id}/curtir")
    public void curtirPost(@PathVariable BigDecimal id) {
        likesService.curtir(id, usuarioLogado.getUsuarioLogado().getId());
    }
    
    @GetMapping("/{id}/descurtir")
    public void descurtirPost(@PathVariable BigDecimal id) {
        likesService.descurtir(id, usuarioLogado.getUsuarioLogado().getId());
    }
    
    @PostMapping
    public Post save(@RequestBody Post cliente) {
        return service.save(cliente);
    }
    
    @PutMapping("/{id}")
    public Post update(@PathVariable BigDecimal id, @RequestBody Post cliente) {
        cliente.setId(id);
        return service.update(id, cliente);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable BigDecimal id) {
        service.delete(id);
    }
    
}
