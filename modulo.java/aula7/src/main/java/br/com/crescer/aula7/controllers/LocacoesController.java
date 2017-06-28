/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula7.controllers;

import br.com.crescer.aula7.entidades.Locacao;
import br.com.crescer.aula7.services.LocacaoService;
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
@RequestMapping("/locacoes")
public class LocacoesController {
    
    @Autowired
    LocacaoService service;
    
    @GetMapping
    public Page<Locacao> findAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        return service.findAll(page, limit);
    }
    
    @GetMapping("/{id}")
    public Locacao findByID(@PathVariable Long id) {
        return service.findByID(id);
    }
    
    @PostMapping
    public Locacao save(@RequestBody Locacao entidade) {
        return service.save(entidade);
    }
    
    @PutMapping("/{id}")
    public Locacao update(@PathVariable Long id, @RequestBody Locacao entidade) {
        entidade.setId(id);
        return service.update(id, entidade);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
}