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
@Table(name = "funcionariocontato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionariocontato.findAll", query = "SELECT f FROM Funcionariocontato f")
    , @NamedQuery(name = "Funcionariocontato.findByFuncionariocontatoId", query = "SELECT f FROM Funcionariocontato f WHERE f.funcionariocontatoId = :funcionariocontatoId")})
public class Funcionariocontato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "funcionariocontato_id")
    private Integer funcionariocontatoId;
    @JoinColumn(name = "contato_id", referencedColumnName = "contato_id")
    @ManyToOne
    private Contato contatoId;
    @JoinColumn(name = "funcionario_id", referencedColumnName = "funcionario_id")
    @ManyToOne
    private Funcionario funcionarioId;

    public Funcionariocontato() {
    }

    public Funcionariocontato(Integer funcionariocontatoId) {
        this.funcionariocontatoId = funcionariocontatoId;
    }

    public Integer getFuncionariocontatoId() {
        return funcionariocontatoId;
    }

    public void setFuncionariocontatoId(Integer funcionariocontatoId) {
        this.funcionariocontatoId = funcionariocontatoId;
    }

    public Contato getContatoId() {
        return contatoId;
    }

    public void setContatoId(Contato contatoId) {
        this.contatoId = contatoId;
    }

    public Funcionario getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Funcionario funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (funcionariocontatoId != null ? funcionariocontatoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionariocontato)) {
            return false;
        }
        Funcionariocontato other = (Funcionariocontato) object;
        if ((this.funcionariocontatoId == null && other.funcionariocontatoId != null) || (this.funcionariocontatoId != null && !this.funcionariocontatoId.equals(other.funcionariocontatoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Funcionariocontato[ funcionariocontatoId=" + funcionariocontatoId + " ]";
    }
    
}
