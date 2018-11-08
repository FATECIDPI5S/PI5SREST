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
@Table(name = "contatotipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contatotipo.findAll", query = "SELECT c FROM Contatotipo c")
    , @NamedQuery(name = "Contatotipo.findByContatotipoId", query = "SELECT c FROM Contatotipo c WHERE c.contatotipoId = :contatotipoId")
    , @NamedQuery(name = "Contatotipo.findByContatotipoDescricao", query = "SELECT c FROM Contatotipo c WHERE c.contatotipoDescricao = :contatotipoDescricao")})
public class Contatotipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "contatotipo_id")
    private Integer contatotipoId;
    @Size(max = 100)
    @Column(name = "contatotipo_descricao")
    private String contatotipoDescricao;
    @OneToMany(mappedBy = "contatotipoId")
    private Collection<Contato> contatoCollection;

    public Contatotipo() {
    }

    public Contatotipo(Integer contatotipoId) {
        this.contatotipoId = contatotipoId;
    }

    public Integer getContatotipoId() {
        return contatotipoId;
    }

    public void setContatotipoId(Integer contatotipoId) {
        this.contatotipoId = contatotipoId;
    }

    public String getContatotipoDescricao() {
        return contatotipoDescricao;
    }

    public void setContatotipoDescricao(String contatotipoDescricao) {
        this.contatotipoDescricao = contatotipoDescricao;
    }

    @XmlTransient
    public Collection<Contato> getContatoCollection() {
        return contatoCollection;
    }

    public void setContatoCollection(Collection<Contato> contatoCollection) {
        this.contatoCollection = contatoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contatotipoId != null ? contatotipoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contatotipo)) {
            return false;
        }
        Contatotipo other = (Contatotipo) object;
        if ((this.contatotipoId == null && other.contatotipoId != null) || (this.contatotipoId != null && !this.contatotipoId.equals(other.contatotipoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Contatotipo[ contatotipoId=" + contatotipoId + " ]";
    }
    
}
