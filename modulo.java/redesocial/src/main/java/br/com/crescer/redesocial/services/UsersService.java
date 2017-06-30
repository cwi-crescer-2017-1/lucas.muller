/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.services;

import br.com.crescer.redesocial.entidades.Usuario;
import br.com.crescer.redesocial.exceptions.NotFoundException;
import br.com.crescer.redesocial.repositorios.UsersRepository;
import java.math.BigDecimal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas.muller
 */
@Service
public class UsersService extends GenericService<Usuario, BigDecimal, UsersRepository> {
    
    public Usuario findByEmail(String email) {
        Usuario et = repo.findOneByEmailIgnoreCase(email);
        if(et != null)
            return et;
        else
            throw new NotFoundException();
    }
    
    @Override
    public Usuario save(Usuario et) {
        return repo.save(criptografarSenha(et));
    }
    
    @Override
    public Usuario update(BigDecimal id, Usuario et) {
        if(repo.exists(id)) {
            if(et.getSenha() != null)
                atualizarSenha(et, et.getSenha());
            else
                et.setSenha(repo.findOne(id).getSenha());
            return repo.save(et);
        } else
            throw new NotFoundException();
    }
    
    public Usuario atualizarSenha(Usuario et, String novaSenha) {
        et.setSenha(novaSenha);
        return update(et.getId(), criptografarSenha(et));
    }
    
    private Usuario criptografarSenha(Usuario et) {
        et.setSenha(getSenhaCriptografada(et.getSenha()));
        return et;
    }
    
    private String getSenhaCriptografada(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }
    
}