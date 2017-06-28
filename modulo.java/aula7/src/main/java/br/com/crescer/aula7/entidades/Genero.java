package br.com.crescer.aula7.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "GENERO")
@SequenceGenerator(name="seq", sequenceName = "SEQ_GENERO", initialValue=1, allocationSize=1)
public class Genero implements Serializable {
    
//    ID NUMBER(12) NOT NULL,
    @Id    
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    
//    DESCRICAO VARCHAR2(60) NOT NULL
    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;

    public Genero() {
    }

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
    
}
