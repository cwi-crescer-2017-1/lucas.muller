package br.com.crescer.aula7.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "VIDEO")
@SequenceGenerator(name="seq", sequenceName = "SEQ_VIDEO", initialValue=1, allocationSize=1)
public class Video implements Serializable {
    
//    ID NUMBER(12) NOT NULL,
    @Id    
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    
//    VALOR DECIMAL(6,2) NOT NULL, 
    @Basic(optional = false)
    @Column(name = "VALOR")
    private double valor;
    
//    DURACAO VARCHAR2(50), 
    @Column(name = "DURACAO")
    private String duracao;
    
//    ID_GENERO NUMBER(12), 
    @Column(name = "ID_GENERO")
    private Long idGenero;
    
//    NOME VARCHAR2(50), 
    @Column(name = "NOME")
    private String nome;
    
//    QUANTIDADE_ESTOQUE INT,
    @Column(name = "QUANTIDADE_ESTOQUE")
    private int quantidade;
    
//    DATA_LANCAMENTO DATE
    @Column(name = "DATA_LANCAMENTO")
    private Date dataLancamento;

    public Video() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public Long getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Long idGenero) {
        this.idGenero = idGenero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
    
}
