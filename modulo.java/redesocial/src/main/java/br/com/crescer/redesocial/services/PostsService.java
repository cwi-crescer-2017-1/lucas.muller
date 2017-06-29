/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.services;

import br.com.crescer.redesocial.entidades.Post;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas.muller
 */
@Service
public class PostsService extends GenericService<Post, BigDecimal, PostsRepository> {
    
}
