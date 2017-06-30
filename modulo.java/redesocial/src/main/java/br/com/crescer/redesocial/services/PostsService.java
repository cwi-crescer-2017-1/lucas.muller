/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.services;

import br.com.crescer.redesocial.controllers.UsuarioLogado;
import br.com.crescer.redesocial.entidades.Post;
import br.com.crescer.redesocial.repositorios.PostsRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas.muller
 */
@Service
public class PostsService extends GenericService<Post, BigDecimal, PostsRepository> {
    
    @Autowired
    private UsuarioLogado usuario;

    @Override
    public Post save(Post et) {
        et.setIdusuario(usuario.getUsuarioLogado());
        return super.save(et);
    }

    @Override
    public Post update(BigDecimal id, Post et) {
        if(usuario.getUsuarioLogado().getId() == et.getIdusuario().getId())
            return super.update(id, et);
        else
            throw new RuntimeException("Você não pode alterar esse post.");
    }

    @Override
    public void delete(BigDecimal id) {
        if(usuario.getUsuarioLogado().getId() == repo.findOne(id).getIdusuario().getId())
            super.delete(id);
        else
            throw new RuntimeException("Você não pode alterar esse post.");
    }
    
}
