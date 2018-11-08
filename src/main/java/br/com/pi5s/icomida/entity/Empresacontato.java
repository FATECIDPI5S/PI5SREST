/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi5s.icomida.entity;

import java.io.Serializable;
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
 * @author Guilherme Abacherli
 */
@Entity
@Table(name = "empresacontato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresacontato.findAll", query = "SELECT e FROM Empresacontato e")
    , @NamedQuery(name = "Empresacontato.findByEmpresacontatoId", query = "SELECT e FROM Empresacontato e WHERE e.empresacontatoId = :empresacontatoId")})
public class Empresacontato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "empresacontato_id")
    private Integer empresacontatoId;
    @JoinColumn(name = "contato_id", referencedColumnName = "contato_id")
    @ManyToOne
    private Contato contatoId;
    @JoinColumn(name = "empresa_id", referencedColumnName = "empresa_id")
    @ManyToOne
    private Empresa empresaId;

    public Empresacontato() {
    }

    public Empresacontato(Integer empresacontatoId) {
        this.empresacontatoId = empresacontatoId;
    }

    public Integer getEmpresacontatoId() {
        return empresacontatoId;
    }

    public void setEmpresacontatoId(Integer empresacontatoId) {
        this.empresacontatoId = empresacontatoId;
    }

    public Contato getContatoId() {
        return contatoId;
    }

    public void setContatoId(Contato contatoId) {
        this.contatoId = contatoId;
    }

    public Empresa getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Empresa empresaId) {
        this.empresaId = empresaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empresacontatoId != null ? empresacontatoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresacontato)) {
            return false;
        }
        Empresacontato other = (Empresacontato) object;
        if ((this.empresacontatoId == null && other.empresacontatoId != null) || (this.empresacontatoId != null && !this.empresacontatoId.equals(other.empresacontatoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Empresacontato[ empresacontatoId=" + empresacontatoId + " ]";
    }
    
}
