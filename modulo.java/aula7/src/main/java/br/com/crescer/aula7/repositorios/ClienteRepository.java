package br.com.crescer.aula7.repositorios;

import br.com.crescer.aula7.entidades.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author lucas.muller
 */
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {
    
}
