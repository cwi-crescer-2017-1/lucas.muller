/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula4.tema;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "LOCACAO")
@SequenceGenerator(name="seq", sequenceName = "SEQ_LOCACAO", initialValue=1, allocationSize=1)
public class Locacao implements Serializable {
    
//    ID NUMBER(12) NOT NULL,
    @Id    
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    
//    VALOR_TOTAL DECIMAL(6,2), 
    @Column(name = "VALOR_TOTAL")
    private double valorTotal;
    
//    ID_FUNCIONARIO NUMBER(12), 
    @Column(name = "ID_FUNCIONARIO")
    private Long idFuncionario;
    
//    ID_CLIENTE NUMBER(12),
    @Column(name = "ID_CLIENTE")
    private Long idCliente;
    
//    ID_VIDEO NUMBER(12),
    @Column(name = "ID_VIDEO")
    private Long idVideo;
    
//    DATA_DEVOLUCAO DATE
    @Column(name = "DATA_DEVOLUCAO")
    private Date dataDevolucao;

    public Locacao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(Long idVideo) {
        this.idVideo = idVideo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    
    
}
