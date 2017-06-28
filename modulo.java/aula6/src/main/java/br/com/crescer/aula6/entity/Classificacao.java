package br.com.crescer.aula6.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author carloshenrique
 */
@Entity
@Table(name = "CLASSIFICACAO")
public class Classificacao implements Serializable {

    private static final String SQ_NAME = "SQ_CLASSIFICACAO";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID_CLASSIFICACAO")
    private Long id;

    @Basic(optional = false)
    @Column(name = "DS_CLASSIFICACAO")
    private String descricao;

    @Basic(optional = false)
    @Column(name = "NR_IDADE")
    private Integer idade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

}
