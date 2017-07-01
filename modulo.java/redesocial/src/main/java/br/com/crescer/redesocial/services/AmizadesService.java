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

    @Override
    public UsuarioAmizade save(UsuarioAmizade et) {
        et.setAtivo('0');
        return super.save(et);
    }
    
    public UsuarioAmizade aceitarAmizade(BigDecimal idAmizade) {
        UsuarioAmizade am = repo.findOne(idAmizade);
        if(am == null)
            throw new NotFoundException();
        
        am.setAtivo('1');
        return super.save(am);
    }
    
}
