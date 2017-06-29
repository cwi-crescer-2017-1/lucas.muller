/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Lucas
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Entidade não encontrada")
public class NotFoundException extends RuntimeException {
    
}
