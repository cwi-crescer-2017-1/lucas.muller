/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.controllers;

import br.com.crescer.redesocial.entidades.UsuarioAmizade;
import br.com.crescer.redesocial.services.AmizadesService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author lucas.muller
 */
@RestController
@RequestMapping("/amizades")
public class AmizadesController {
    
    @Autowired
    AmizadesService service;
    
    @PostMapping
    public UsuarioAmizade novaAmizade(@RequestBody UsuarioAmizade et) {
        return service.save(et);
    }
    
    @GetMapping("/{id}/aceitar")
    public UsuarioAmizade aceitarAmizade(@PathVariable BigDecimal id) {
        return service.aceitarAmizade(id);
    }
    
    @DeleteMapping("/{id}")
    public void removerAmizade(@PathVariable BigDecimal id) {
        service.delete(id);
    }
    
}
