/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula7.controllers;

import br.com.crescer.aula7.entidades.Funcionario;
import br.com.crescer.aula7.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Lucas
 */
@RestController
@RequestMapping("/funcionarios")
public class FuncionariosController {
    
    @Autowired
    FuncionarioService service;
    
    @GetMapping
    public Page<Funcionario> findAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        return service.findAll(page, limit);
    }
    
    @GetMapping("/{id}")
    public Funcionario findByID(@PathVariable Long id) {
        return service.findByID(id);
    }
    
    @PostMapping
    public Funcionario save(@RequestBody Funcionario entidade) {
        return service.save(entidade);
    }
    
    @PutMapping("/{id}")
    public Funcionario update(@PathVariable Long id, @RequestBody Funcionario entidade) {
        entidade.setId(id);
        return service.update(id, entidade);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
}
