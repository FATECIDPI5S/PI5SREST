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
@Table(name = "funcionarioendereco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionarioendereco.findAll", query = "SELECT f FROM Funcionarioendereco f")
    , @NamedQuery(name = "Funcionarioendereco.findByFuncionarioenderecoId", query = "SELECT f FROM Funcionarioendereco f WHERE f.funcionarioenderecoId = :funcionarioenderecoId")})
public class Funcionarioendereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "funcionarioendereco_id")
    private Integer funcionarioenderecoId;
    @JoinColumn(name = "endereco_id", referencedColumnName = "endereco_id")
    @ManyToOne
    private Endereco enderecoId;
    @JoinColumn(name = "funcionario_id", referencedColumnName = "funcionario_id")
    @ManyToOne
    private Funcionario funcionarioId;

    public Funcionarioendereco() {
    }

    public Funcionarioendereco(Integer funcionarioenderecoId) {
        this.funcionarioenderecoId = funcionarioenderecoId;
    }

    public Integer getFuncionarioenderecoId() {
        return funcionarioenderecoId;
    }

    public void setFuncionarioenderecoId(Integer funcionarioenderecoId) {
        this.funcionarioenderecoId = funcionarioenderecoId;
    }

    public Endereco getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Endereco enderecoId) {
        this.enderecoId = enderecoId;
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
        hash += (funcionarioenderecoId != null ? funcionarioenderecoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionarioendereco)) {
            return false;
        }
        Funcionarioendereco other = (Funcionarioendereco) object;
        if ((this.funcionarioenderecoId == null && other.funcionarioenderecoId != null) || (this.funcionarioenderecoId != null && !this.funcionarioenderecoId.equals(other.funcionarioenderecoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Funcionarioendereco[ funcionarioenderecoId=" + funcionarioenderecoId + " ]";
    }
    
}
