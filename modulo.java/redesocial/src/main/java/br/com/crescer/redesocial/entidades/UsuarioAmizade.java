/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.muller
 */
@Entity
@Table(name = "USUARIO_AMIZADE")
@XmlRootElement
@SequenceGenerator(name="seq", sequenceName = "USUARIO_AMIZADE_SEQ", initialValue=1, allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "UsuarioAmizade.findAll", query = "SELECT u FROM UsuarioAmizade u"),
    @NamedQuery(name = "UsuarioAmizade.findById", query = "SELECT u FROM UsuarioAmizade u WHERE u.id = :id"),
    @NamedQuery(name = "UsuarioAmizade.findByAtivo", query = "SELECT u FROM UsuarioAmizade u WHERE u.ativo = :ativo")})
public class UsuarioAmizade implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ATIVO")
    private Character ativo;
    @JoinColumn(name = "IDUSUARIO1", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario idusuario1;
    @JoinColumn(name = "IDUSUARIO2", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario idusuario2;

    public UsuarioAmizade() {
    }

    public UsuarioAmizade(BigDecimal id) {
        this.id = id;
    }

    public UsuarioAmizade(BigDecimal id, Character ativo) {
        this.id = id;
        this.ativo = ativo;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Character getAtivo() {
        return ativo;
    }

    public void setAtivo(Character ativo) {
        this.ativo = ativo;
    }

    public Usuario getIdusuario1() {
        return idusuario1;
    }

    public void setIdusuario1(Usuario idusuario1) {
        this.idusuario1 = idusuario1;
    }

    public Usuario getIdusuario2() {
        return idusuario2;
    }

    public void setIdusuario2(Usuario idusuario2) {
        this.idusuario2 = idusuario2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioAmizade)) {
            return false;
        }
        UsuarioAmizade other = (UsuarioAmizade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.crescer.redesocial.entidades.UsuarioAmizade[ id=" + id + " ]";
    }
    
}
