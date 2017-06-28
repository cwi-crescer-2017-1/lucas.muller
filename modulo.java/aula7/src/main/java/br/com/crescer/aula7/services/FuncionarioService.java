/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula7.services;

import br.com.crescer.aula7.exceptions.FuncionarioNotFoundException;
import br.com.crescer.aula4.tema.Funcionario;
import br.com.crescer.aula7.repositorios.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author lucas.muller
 */
public final class FuncionarioService implements BasicService<Funcionario, Long> {
    
    @Autowired
    FuncionarioRepository repo;

    @Override
    public Page<Funcionario> findAll(Integer page, Integer limit) {
        return repo.findAll(new PageRequest(page == null ? 0 : page, limit == null || limit == 0 ? 10 : limit));
    }

    @Override
    public Funcionario findByID(Long id) {
        Funcionario cli = repo.findOne(id);
        if(cli != null)
            return cli;
        else
            throw new FuncionarioNotFoundException();
    }

    @Override
    public Funcionario save(Funcionario et) {
        return repo.save(et);
    }

    @Override
    public Funcionario update(Funcionario et) {
        if(repo.exists(et.getId()))
            return repo.save(et);
        else
            throw new FuncionarioNotFoundException();
    }

    @Override
    public void delete(Long id) {
        if(repo.exists(id))
            repo.delete(id);
        else
            throw new FuncionarioNotFoundException();
    }
    
}
