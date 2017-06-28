package br.com.crescer.aula7.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "CLIENTE")
@SequenceGenerator(name="seq", sequenceName = "SEQ_CLIENTE", initialValue=1, allocationSize=1)
public class Cliente implements Serializable {
    
//    ID NUMBER(12) NOT NULL,
    @Id    
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    
//    NOME VARCHAR2(50) NOT NULL,
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    
//    CPF VARCHAR2(11) NOT NULL,
    @Basic(optional = false)
    @Column(name = "CPF")
    private String cpf;
    
//    RG VARCHAR2(15), 
    @Column(name = "RG")
    private String rg;
    
//    RUA VARCHAR2(50),
    @Column(name = "RUA")
    private String rua;
    
//    BAIRRO VARCHAR2(50), 
    @Column(name = "BAIRRO")
    private String bairro;
    
//    CIDADE VARCHAR2(50), 
    @Column(name = "CIDADE")
    private String cidade;
    
//    NUMERO_CASA VARCHAR2(50), 
    @Column(name = "NUMERO_CASA")
    private String numeroCasa;
    
//    EMAIL VARCHAR2(50), 
    @Column(name = "EMAIL")
    private String email;

//    TELEFONE VARCHAR2(50), 
    @Column(name = "TELEFONE")
    private String telefone;
    
//    CELULAR VARCHAR2(50) NOT NULL,
    @Basic(optional = false)
    @Column(name = "CELULAR")
    private String celular;
    
//    NASCIMENTO DATE
    @Column(name = "NASCIMENTO")
    private Date nascimento;

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }
    
    
}
