/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.controllers;

import br.com.crescer.redesocial.entidades.Post;
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
    
    @GetMapping
    public Page<Post> findAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        return service.findAll(page, limit);
    }
    
    @GetMapping("/{id}")
    public Post findByID(@PathVariable BigDecimal id) {
        return service.findByID(id);
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
