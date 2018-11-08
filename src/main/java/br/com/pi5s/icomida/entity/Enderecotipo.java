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
@Table(name = "enderecotipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enderecotipo.findAll", query = "SELECT e FROM Enderecotipo e")
    , @NamedQuery(name = "Enderecotipo.findByEnderecotipoId", query = "SELECT e FROM Enderecotipo e WHERE e.enderecotipoId = :enderecotipoId")
    , @NamedQuery(name = "Enderecotipo.findByEnderecotipoDescricao", query = "SELECT e FROM Enderecotipo e WHERE e.enderecotipoDescricao = :enderecotipoDescricao")})
public class Enderecotipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "enderecotipo_id")
    private Integer enderecotipoId;
    @Size(max = 100)
    @Column(name = "enderecotipo_descricao")
    private String enderecotipoDescricao;
    @OneToMany(mappedBy = "enderecotipoId")
    private Collection<Endereco> enderecoCollection;

    public Enderecotipo() {
    }

    public Enderecotipo(Integer enderecotipoId) {
        this.enderecotipoId = enderecotipoId;
    }

    public Integer getEnderecotipoId() {
        return enderecotipoId;
    }

    public void setEnderecotipoId(Integer enderecotipoId) {
        this.enderecotipoId = enderecotipoId;
    }

    public String getEnderecotipoDescricao() {
        return enderecotipoDescricao;
    }

    public void setEnderecotipoDescricao(String enderecotipoDescricao) {
        this.enderecotipoDescricao = enderecotipoDescricao;
    }

    @XmlTransient
    public Collection<Endereco> getEnderecoCollection() {
        return enderecoCollection;
    }

    public void setEnderecoCollection(Collection<Endereco> enderecoCollection) {
        this.enderecoCollection = enderecoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enderecotipoId != null ? enderecotipoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enderecotipo)) {
            return false;
        }
        Enderecotipo other = (Enderecotipo) object;
        if ((this.enderecotipoId == null && other.enderecotipoId != null) || (this.enderecotipoId != null && !this.enderecotipoId.equals(other.enderecotipoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Enderecotipo[ enderecotipoId=" + enderecotipoId + " ]";
    }
    
}
