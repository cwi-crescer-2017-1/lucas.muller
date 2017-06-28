/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula7.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author lucas.muller
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Cliente não encontrado")
public class ClienteNotFoundException extends RuntimeException {
    
}
