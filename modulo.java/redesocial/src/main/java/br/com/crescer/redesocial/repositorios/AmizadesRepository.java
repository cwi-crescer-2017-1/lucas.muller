/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.repositorios;

import br.com.crescer.redesocial.entidades.UsuarioAmizade;
import java.math.BigDecimal;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author lucas.muller
 */
public interface AmizadesRepository extends PagingAndSortingRepository<UsuarioAmizade, BigDecimal> {
    
}
