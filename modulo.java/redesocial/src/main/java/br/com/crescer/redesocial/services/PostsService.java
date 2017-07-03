/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.services;

import br.com.crescer.redesocial.controllers.UsuarioLogado;
import br.com.crescer.redesocial.entidades.Post;
import br.com.crescer.redesocial.entidades.Usuario;
import br.com.crescer.redesocial.entidades.UsuarioAmizade;
import br.com.crescer.redesocial.repositorios.PostsRepository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<Post> findAll(Integer page, Integer limit) {
        return repo.findAllByOrderByDataDesc(makePageable(page, limit));
    }
    
    public Page<Post> findPostsContaining(Integer page, Integer limit, String texto) {
        return repo.findAllByTextoContainingIgnoreCaseOrderByDataDesc(makePageable(page, limit), texto);
    }

    @Override
    public Post save(Post et) {
        et.setData(new Date());
        et.setIdusuario(usuario.getUsuarioLogado());
        return super.save(et);
    }

    @Override
    public Post update(BigDecimal id, Post et) {
        if(usuario.getUsuarioLogado().getId().intValueExact() == super.findByID(id).getIdusuario().getId().intValueExact())
            return super.update(id, et);
        else
            throw new RuntimeException("Você não pode alterar esse post.");
    }

    @Override
    public void delete(BigDecimal id) {
        if(usuario.getUsuarioLogado().getId().intValueExact() == super.findByID(id).getIdusuario().getId().intValueExact())
            super.delete(id);
        else
            throw new RuntimeException("Você não pode alterar esse post.");
    }
    
    
    public Page<Post> getFeed(Integer page, Integer limit) {
        final Usuario usuarioLogado = usuario.getUsuarioLogado();
        
        final Set<BigDecimal> amigos = usuarioLogado.getUsuarioAmizadeCollection().stream()
                .filter(amizade -> amizade.getAtivo() == '1')
                .map(UsuarioAmizade::getIdusuario2)
                .map(Usuario::getId)
                .collect(toSet());
        
        usuarioLogado.getUsuarioAmizadeCollection1().stream()
                .filter(amizade -> amizade.getAtivo() == '1')
                .map(UsuarioAmizade::getIdusuario1)
                .map(Usuario::getId)
                .forEach(amigos::add);
        
        amigos.add(usuarioLogado.getId());
        
        return repo.findByIdusuario_idInOrderByDataDesc(makePageable(page, limit), amigos);        
    }
    
}
