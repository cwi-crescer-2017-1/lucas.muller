/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.services;

import br.com.crescer.redesocial.controllers.UsuarioLogado;
import br.com.crescer.redesocial.entidades.UsuarioAmizade;
import br.com.crescer.redesocial.exceptions.NotFoundException;
import br.com.crescer.redesocial.repositorios.AmizadesRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas.muller
 */
@Service
public class AmizadesService extends GenericService<UsuarioAmizade, BigDecimal, AmizadesRepository> {

    @Autowired
    private UsuarioLogado usuario;
    
    @Override
    public UsuarioAmizade save(UsuarioAmizade et) {
        if(et.getIdusuario1().getId().intValueExact() == et.getIdusuario2().getId().intValueExact())
            throw new RuntimeException("Usuários são iguais.");
        // usuário 1 é aquele q enviou a solicitação
        // se for nulo, setar para o usuário logado
        if(et.getIdusuario1() == null)
            et.setIdusuario1(usuario.getUsuarioLogado());
        et.setAtivo('0');
        return super.save(et);
    }
    
    public UsuarioAmizade aceitarAmizade(BigDecimal idAmizade) {
        UsuarioAmizade am = repo.findOne(idAmizade);
        if(am == null)
            throw new NotFoundException("Solicitação não encontrada");
        
        am.setAtivo('1');
        return super.save(am);
    }
    
}
