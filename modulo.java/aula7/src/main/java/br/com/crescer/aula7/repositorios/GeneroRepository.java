/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula7.repositorios;

import br.com.crescer.aula7.entidades.Genero;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author lucas.muller
 */
public interface GeneroRepository extends PagingAndSortingRepository<Genero, Long> {
    
}
