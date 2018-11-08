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
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")
    , @NamedQuery(name = "Empresa.findByEmpresaId", query = "SELECT e FROM Empresa e WHERE e.empresaId = :empresaId")
    , @NamedQuery(name = "Empresa.findByEmpresaRazaoSocial", query = "SELECT e FROM Empresa e WHERE e.empresaRazaoSocial = :empresaRazaoSocial")
    , @NamedQuery(name = "Empresa.findByEmpresaNomeFantasia", query = "SELECT e FROM Empresa e WHERE e.empresaNomeFantasia = :empresaNomeFantasia")
    , @NamedQuery(name = "Empresa.findByEmpresaCnpj", query = "SELECT e FROM Empresa e WHERE e.empresaCnpj = :empresaCnpj")
    , @NamedQuery(name = "Empresa.findByEmpresaIe", query = "SELECT e FROM Empresa e WHERE e.empresaIe = :empresaIe")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "empresa_id")
    private Integer empresaId;
    @Size(max = 255)
    @Column(name = "empresa_razao_social")
    private String empresaRazaoSocial;
    @Size(max = 255)
    @Column(name = "empresa_nome_fantasia")
    private String empresaNomeFantasia;
    @Size(max = 15)
    @Column(name = "empresa_cnpj")
    private String empresaCnpj;
    @Size(max = 15)
    @Column(name = "empresa_ie")
    private String empresaIe;
    @OneToMany(mappedBy = "empresaId")
    private Collection<Ambiente> ambienteCollection;
    @OneToMany(mappedBy = "empresaId")
    private Collection<Empresaendereco> empresaenderecoCollection;
    @OneToMany(mappedBy = "empresaId")
    private Collection<Produto> produtoCollection;
    @OneToMany(mappedBy = "empresaId")
    private Collection<Pedido> pedidoCollection;
    @OneToMany(mappedBy = "empresaId")
    private Collection<Empresacontato> empresacontatoCollection;
    @OneToMany(mappedBy = "empresaId")
    private Collection<Funcionario> funcionarioCollection;
    @OneToMany(mappedBy = "empresaMatriz")
    private Collection<Empresa> empresaCollection;
    @JoinColumn(name = "empresa_matriz", referencedColumnName = "empresa_id")
    @ManyToOne
    private Empresa empresaMatriz;

    public Empresa() {
    }

    public Empresa(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public String getEmpresaRazaoSocial() {
        return empresaRazaoSocial;
    }

    public void setEmpresaRazaoSocial(String empresaRazaoSocial) {
        this.empresaRazaoSocial = empresaRazaoSocial;
    }

    public String getEmpresaNomeFantasia() {
        return empresaNomeFantasia;
    }

    public void setEmpresaNomeFantasia(String empresaNomeFantasia) {
        this.empresaNomeFantasia = empresaNomeFantasia;
    }

    public String getEmpresaCnpj() {
        return empresaCnpj;
    }

    public void setEmpresaCnpj(String empresaCnpj) {
        this.empresaCnpj = empresaCnpj;
    }

    public String getEmpresaIe() {
        return empresaIe;
    }

    public void setEmpresaIe(String empresaIe) {
        this.empresaIe = empresaIe;
    }

    @XmlTransient
    public Collection<Ambiente> getAmbienteCollection() {
        return ambienteCollection;
    }

    public void setAmbienteCollection(Collection<Ambiente> ambienteCollection) {
        this.ambienteCollection = ambienteCollection;
    }

    @XmlTransient
    public Collection<Empresaendereco> getEmpresaenderecoCollection() {
        return empresaenderecoCollection;
    }

    public void setEmpresaenderecoCollection(Collection<Empresaendereco> empresaenderecoCollection) {
        this.empresaenderecoCollection = empresaenderecoCollection;
    }

    @XmlTransient
    public Collection<Produto> getProdutoCollection() {
        return produtoCollection;
    }

    public void setProdutoCollection(Collection<Produto> produtoCollection) {
        this.produtoCollection = produtoCollection;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    @XmlTransient
    public Collection<Empresacontato> getEmpresacontatoCollection() {
        return empresacontatoCollection;
    }

    public void setEmpresacontatoCollection(Collection<Empresacontato> empresacontatoCollection) {
        this.empresacontatoCollection = empresacontatoCollection;
    }

    @XmlTransient
    public Collection<Funcionario> getFuncionarioCollection() {
        return funcionarioCollection;
    }

    public void setFuncionarioCollection(Collection<Funcionario> funcionarioCollection) {
        this.funcionarioCollection = funcionarioCollection;
    }

    @XmlTransient
    public Collection<Empresa> getEmpresaCollection() {
        return empresaCollection;
    }

    public void setEmpresaCollection(Collection<Empresa> empresaCollection) {
        this.empresaCollection = empresaCollection;
    }

    public Empresa getEmpresaMatriz() {
        return empresaMatriz;
    }

    public void setEmpresaMatriz(Empresa empresaMatriz) {
        this.empresaMatriz = empresaMatriz;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empresaId != null ? empresaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.empresaId == null && other.empresaId != null) || (this.empresaId != null && !this.empresaId.equals(other.empresaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Empresa[ empresaId=" + empresaId + " ]";
    }
    
}
