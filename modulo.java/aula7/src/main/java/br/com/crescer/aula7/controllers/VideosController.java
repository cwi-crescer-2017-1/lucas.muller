/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula7.controllers;

import br.com.crescer.aula7.entidades.Video;
import br.com.crescer.aula7.services.VideoService;
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
@RequestMapping("/videos")
public class VideosController {
    
    @Autowired
    VideoService service;
    
    @GetMapping
    public Page<Video> findAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        return service.findAll(page, limit);
    }
    
    @GetMapping("/{id}")
    public Video findByID(@PathVariable Long id) {
        return service.findByID(id);
    }
    
    @PostMapping
    public Video save(@RequestBody Video entidade) {
        return service.save(entidade);
    }
    
    @PutMapping("/{id}")
    public Video update(@PathVariable Long id, @RequestBody Video entidade) {
        entidade.setId(id);
        return service.update(id, entidade);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
}
