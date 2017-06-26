/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula4.tema;

/**
 *
 * @author lucas.muller
 */
public class LocacaoDao extends CrudDaoImpl<Locacao, Long> {
    
    public LocacaoDao() {
        super(Locacao.class);
    }
    
}
