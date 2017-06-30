/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.services;

import br.com.crescer.redesocial.entidades.Post;
import br.com.crescer.redesocial.entidades.PostLike;
import br.com.crescer.redesocial.entidades.Usuario;
import br.com.crescer.redesocial.exceptions.NotFoundException;
import br.com.crescer.redesocial.repositorios.LikesRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas.muller
 */
@Service
public class LikesService extends GenericService<PostLike, BigDecimal, LikesRepository> {
    
    @Autowired
    private PostsService postsService;
    
    @Autowired
    private UsersService usersService;
    
    public void curtir(BigDecimal idPost, BigDecimal idUsuario) {
        PostLike like = new PostLike();
        Post post = postsService.findByID(idPost);
        Usuario usuario = usersService.findByID(idUsuario);
        like.setIdpost(post);
        like.setIdusuario(usuario);
        repo.save(like);
    }
    
    public void descurtir(BigDecimal idPost, BigDecimal idUsuario) {
        PostLike like = repo.findOneByIdpost_idAndIdusuario_id(idPost, idUsuario);
        if(like == null)
            throw new NotFoundException();
        repo.delete(like.getId());
    }
    
}
