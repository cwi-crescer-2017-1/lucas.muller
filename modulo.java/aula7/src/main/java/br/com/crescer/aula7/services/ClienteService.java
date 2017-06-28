package br.com.crescer.aula7.services;

import br.com.crescer.aula7.entidades.Cliente;
import br.com.crescer.aula7.exceptions.ClienteNotFoundException;
import br.com.crescer.aula7.repositorios.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas.muller
 */
@Service
public final class ClienteService implements BasicService<Cliente, Long> {
    
    @Autowired
    ClienteRepository repo;
    
    public Iterable<Cliente> findAll() {
        return repo.findAll();
    }
    
    public Page<Cliente> findAll(Integer page, Integer limit) {
        return repo.findAll(new PageRequest(page == null ? 0 : page, limit == null || limit == 0 ? 10 : limit));
    }
    
    public Cliente findByID(Long id) {
        Cliente cli = repo.findOne(id);
        if(cli != null)
            return cli;
        else
            throw new ClienteNotFoundException();
    }
    
    public Cliente save(Cliente cli) {
        return repo.save(cli);
    }
    
    public Cliente update(Cliente cli) {
        if(repo.exists(cli.getId()))
            return repo.save(cli);
        else
            throw new ClienteNotFoundException();
    }
    
    public void delete(Long id) {
        if(repo.exists(id))
            repo.delete(id);
        else
            throw new ClienteNotFoundException();
    }
    
}
