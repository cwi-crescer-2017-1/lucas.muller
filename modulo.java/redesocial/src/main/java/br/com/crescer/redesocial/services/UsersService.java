/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.services;

import br.com.crescer.redesocial.entidades.Post;
import br.com.crescer.redesocial.entidades.Usuario;
import br.com.crescer.redesocial.exceptions.NotFoundException;
import br.com.crescer.redesocial.repositorios.UsersRepository;
import static br.com.crescer.redesocial.services.GenericService.MIN_LIMIT;
import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    
    public Collection<Post> findPostsByUserID(BigDecimal id) {
        Usuario user = super.findByID(id);
        return user.getPostCollection();
    }
    
    public Iterable<Usuario> findBySearch(String termo) {
        return repo.findByNomeContainingIgnoreCase(termo);
    }
    
    public Page<Usuario> findBySearch(Integer page, Integer limit, String termo) {
        return repo.findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCase(new PageRequest(
                page == null ? 0 : page, 
                limit == null || limit == 0 ? MIN_LIMIT : limit
        ), termo, termo);
    }
    
    @Override
    public Usuario save(Usuario et) {
        if(repo.findOneByEmailIgnoreCase(et.getEmail()) != null)
            throw new RuntimeException("Email já está em uso por outro usuário");
        return repo.save(criptografarSenha(et));
    }
    
    @Override
    public Usuario update(BigDecimal id, Usuario et) {
        Usuario usuario = repo.findOne(id); // usuario do banco
        if(usuario == null)
            throw new NotFoundException("Usuário não encontrado");
        
        et.setEmail(usuario.getEmail()); // para não ser possível trocar de email
        if(et.getSenha() != null)
            et.setSenha(getSenhaCriptografada(et.getSenha()));
        else
            et.setSenha(usuario.getSenha());
        return repo.save(et);
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
        if(senha == null || senha.trim().length() == 0)
            throw new RuntimeException("A senha não pode ser nula ou vazia.");
        return new BCryptPasswordEncoder().encode(senha);
    }
    
}