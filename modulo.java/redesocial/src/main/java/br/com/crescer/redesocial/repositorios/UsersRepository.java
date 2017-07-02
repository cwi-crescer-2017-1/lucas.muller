/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.repositorios;

import br.com.crescer.redesocial.entidades.Usuario;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author lucas.muller
 */
public interface UsersRepository extends PagingAndSortingRepository<Usuario, BigDecimal> {
    
    Usuario findOneByEmailIgnoreCase(String email);
    
    Iterable<Usuario> findByNomeContainingIgnoreCase(String nome);
    
    Page<Usuario> findByNomeContainingIgnoreCase(Pageable pgbl, String nome);
    
    Page<Usuario> findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCase(Pageable pgbl, String nome, String email);
    
}
