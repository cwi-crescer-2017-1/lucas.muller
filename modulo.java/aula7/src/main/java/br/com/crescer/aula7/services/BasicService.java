/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula7.services;

import org.springframework.data.domain.Page;

/**
 *
 * @author lucas.muller
 */
public interface BasicService<Entidade, ID> {
    
    Page<Entidade> findAll(Integer page, Integer limit);
    
    Entidade findByID(ID id);
    
    Entidade save(Entidade et);
    
    Entidade update(Entidade et);
    
    void delete(ID id);
}
