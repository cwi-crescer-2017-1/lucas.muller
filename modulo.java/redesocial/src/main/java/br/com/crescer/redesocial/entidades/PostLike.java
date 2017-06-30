/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.muller
 */
@Entity
@Table(name = "POST_LIKE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PostLike.findAll", query = "SELECT p FROM PostLike p"),
    @NamedQuery(name = "PostLike.findById", query = "SELECT p FROM PostLike p WHERE p.id = :id")})
public class PostLike implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    
    @JoinColumn(name = "IDPOST", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Post idpost;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Usuario idusuario;
    @Column(name = "IDUSUARIO", insertable = false, updatable = false)
    @JsonProperty("idusuario")
    private BigDecimal idusuario2;

    public BigDecimal getIdusuario2() {
        return idusuario2;
    }

    public PostLike() {
    }

    public PostLike(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Post getIdpost() {
        return idpost;
    }

    public void setIdpost(Post idpost) {
        this.idpost = idpost;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
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
        if (!(object instanceof PostLike)) {
            return false;
        }
        PostLike other = (PostLike) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.crescer.redesocial.entidades.PostLike[ id=" + id + " ]";
    }
    
}
