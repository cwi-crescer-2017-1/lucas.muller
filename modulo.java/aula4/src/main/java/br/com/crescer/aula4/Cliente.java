/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula4;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Id;

/**
 *
 * @author lucas.muller
 */
@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

    @Id    
    @Basic(optional = false)
    @Column(name = "ID_CLIENTE")
    private Long idPessoa;

    public Cliente() { }
    
    public Cliente(Long idPessoa, String nmPessoa) {
        this.idPessoa = idPessoa;
        this.nmPessoa = nmPessoa;
    }

    @Basic(optional = false)
    @Column(name = "NM_CLIENTE")
    private String nmPessoa;

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNmPessoa() {
        return nmPessoa;
    }

    public void setNmPessoa(String nmPessoa) {
        this.nmPessoa = nmPessoa;
    }
    
}
