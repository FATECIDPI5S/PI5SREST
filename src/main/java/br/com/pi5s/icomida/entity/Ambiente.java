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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ambiente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ambiente.findAll", query = "SELECT a FROM Ambiente a")
    , @NamedQuery(name = "Ambiente.findByAmbienteId", query = "SELECT a FROM Ambiente a WHERE a.ambienteId = :ambienteId")
    , @NamedQuery(name = "Ambiente.findByAmbienteNome", query = "SELECT a FROM Ambiente a WHERE a.ambienteNome = :ambienteNome")
    , @NamedQuery(name = "Ambiente.findByAmbienteOrdem", query = "SELECT a FROM Ambiente a WHERE a.ambienteOrdem = :ambienteOrdem")})
public class Ambiente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ambiente_id")
    private Integer ambienteId;
    @Size(max = 45)
    @Column(name = "ambiente_nome")
    private String ambienteNome;
    @Column(name = "ambiente_ordem")
    private Integer ambienteOrdem;
    @JoinColumn(name = "empresa_id", referencedColumnName = "empresa_id")
    @ManyToOne
    private Empresa empresaId;
    @OneToMany(mappedBy = "ambienteId")
    private Collection<Mesa> mesaCollection;

    public Ambiente() {
    }

    public Ambiente(Integer ambienteId) {
        this.ambienteId = ambienteId;
    }

    public Integer getAmbienteId() {
        return ambienteId;
    }

    public void setAmbienteId(Integer ambienteId) {
        this.ambienteId = ambienteId;
    }

    public String getAmbienteNome() {
        return ambienteNome;
    }

    public void setAmbienteNome(String ambienteNome) {
        this.ambienteNome = ambienteNome;
    }

    public Integer getAmbienteOrdem() {
        return ambienteOrdem;
    }

    public void setAmbienteOrdem(Integer ambienteOrdem) {
        this.ambienteOrdem = ambienteOrdem;
    }

    public Empresa getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Empresa empresaId) {
        this.empresaId = empresaId;
    }

    @XmlTransient
    public Collection<Mesa> getMesaCollection() {
        return mesaCollection;
    }

    public void setMesaCollection(Collection<Mesa> mesaCollection) {
        this.mesaCollection = mesaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ambienteId != null ? ambienteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ambiente)) {
            return false;
        }
        Ambiente other = (Ambiente) object;
        if ((this.ambienteId == null && other.ambienteId != null) || (this.ambienteId != null && !this.ambienteId.equals(other.ambienteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Ambiente[ ambienteId=" + ambienteId + " ]";
    }
    
}
