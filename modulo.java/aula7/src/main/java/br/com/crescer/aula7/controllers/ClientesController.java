/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula7.controllers;

import br.com.crescer.aula7.entidades.Cliente;
import br.com.crescer.aula7.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author lucas.muller
 */
@RestController
@RequestMapping("/clientes")
public class ClientesController {
    
    @Autowired
    ClienteService service;
    
    @GetMapping
    public Page<Cliente> findAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        return service.findAll(page, limit);
    }
    
    @GetMapping("/{id}")
    public Cliente findByID(@PathVariable Long id) {
        return service.findByID(id);
    }
    
    @PostMapping
    public Cliente save(@RequestBody Cliente cliente) {
        return service.save(cliente);
    }
    
    @PutMapping("/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return service.update(cliente);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
}
