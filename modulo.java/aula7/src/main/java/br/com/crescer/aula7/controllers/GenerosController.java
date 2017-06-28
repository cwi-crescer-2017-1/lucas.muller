/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula7.controllers;

import br.com.crescer.aula7.entidades.Genero;
import br.com.crescer.aula7.services.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lucas
 */
@RestController
@RequestMapping("/generos")
public class GenerosController {
    
    @Autowired
    GeneroService service;
    
    @GetMapping
    public Page<Genero> findAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        return service.findAll(page, limit);
    }
    
    @GetMapping("/{id}")
    public Genero findByID(@PathVariable Long id) {
        return service.findByID(id);
    }
    
    @PostMapping
    public Genero save(@RequestBody Genero entidade) {
        return service.save(entidade);
    }
    
    @PutMapping("/{id}")
    public Genero update(@PathVariable Long id, @RequestBody Genero entidade) {
        entidade.setId(id);
        return service.update(id, entidade);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
}
