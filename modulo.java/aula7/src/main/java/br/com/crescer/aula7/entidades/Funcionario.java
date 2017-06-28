package br.com.crescer.aula7.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "FUNCIONARIO")
@SequenceGenerator(name="seq", sequenceName = "SEQ_FUNCIONARIO", initialValue=1, allocationSize=1)
public class Funcionario implements Serializable {
    
//    ID NUMBER(12) NOT NULL,
    @Id    
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    
//    NOME VARCHAR2(60) NOT NULL,
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    
//    BAIRRO VARCHAR2(60),
    @Column(name = "BAIRRO")
    private String bairro;
    
//    CIDADE VARCHAR2(60),
    @Column(name = "CIDADE")
    private String cidade;
    
//    NUMERO_CASA VARCHAR2(10),
    @Column(name = "NUMERO_CASA")
    private String numeroCasa;
    
//    RUA VARCHAR2(80),
    @Column(name = "RUA")
    private String rua;
    
//    RG VARCHAR2(15) NOT NULL,
    @Basic(optional = false)
    @Column(name = "RG")
    private String rg;
    
//    EMAIL VARCHAR2(100),
    @Column(name = "EMAIL")
    private String email;
    
//    TELEFONE VARCHAR2(50),
    @Column(name = "TELEFONE")
    private String telefone;
    
//    CELULAR VARCHAR2(50),
    @Column(name = "CELULAR")
    private String celular;
    
//    SALARIO DECIMAL(10,2),
    @Column(name = "SALARIO")
    private double salario;
    
//    FUNCAO VARCHAR2(50),
    @Column(name = "FUNCAO")
    private String funcao;
    
//    CPF VARCHAR2(11),
    @Column(name = "CPF")
    private String cpf;
    
//    NASCIMENTO DATE
    @Column(name = "NASCIMENTO")
    private Date nascimento;
    
    public Funcionario() {
        
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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
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

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }
    
    
}
