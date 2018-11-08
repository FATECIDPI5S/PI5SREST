/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi5s.icomida.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Guilherme Abacherli
 */
@Entity
@Table(name = "stts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stts.findAll", query = "SELECT s FROM Stts s")
    , @NamedQuery(name = "Stts.findBySttsId", query = "SELECT s FROM Stts s WHERE s.sttsId = :sttsId")
    , @NamedQuery(name = "Stts.findBySttsDescricao", query = "SELECT s FROM Stts s WHERE s.sttsDescricao = :sttsDescricao")})
public class Stts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "stts_id")
    private Integer sttsId;
    @Size(max = 50)
    @Column(name = "stts_descricao")
    private String sttsDescricao;
    @OneToMany(mappedBy = "sttsId")
    private Collection<Produto> produtoCollection;
    @OneToMany(mappedBy = "sttsId")
    private Collection<Funcionario> funcionarioCollection;
    @OneToMany(mappedBy = "sttsId")
    private Collection<Produtovenda> produtovendaCollection;

    public Stts() {
    }

    public Stts(Integer sttsId) {
        this.sttsId = sttsId;
    }

    public Integer getSttsId() {
        return sttsId;
    }

    public void setSttsId(Integer sttsId) {
        this.sttsId = sttsId;
    }

    public String getSttsDescricao() {
        return sttsDescricao;
    }

    public void setSttsDescricao(String sttsDescricao) {
        this.sttsDescricao = sttsDescricao;
    }

    @XmlTransient
    public Collection<Produto> getProdutoCollection() {
        return produtoCollection;
    }

    public void setProdutoCollection(Collection<Produto> produtoCollection) {
        this.produtoCollection = produtoCollection;
    }

    @XmlTransient
    public Collection<Funcionario> getFuncionarioCollection() {
        return funcionarioCollection;
    }

    public void setFuncionarioCollection(Collection<Funcionario> funcionarioCollection) {
        this.funcionarioCollection = funcionarioCollection;
    }

    @XmlTransient
    public Collection<Produtovenda> getProdutovendaCollection() {
        return produtovendaCollection;
    }

    public void setProdutovendaCollection(Collection<Produtovenda> produtovendaCollection) {
        this.produtovendaCollection = produtovendaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sttsId != null ? sttsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stts)) {
            return false;
        }
        Stts other = (Stts) object;
        if ((this.sttsId == null && other.sttsId != null) || (this.sttsId != null && !this.sttsId.equals(other.sttsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Stts[ sttsId=" + sttsId + " ]";
    }
    
}
