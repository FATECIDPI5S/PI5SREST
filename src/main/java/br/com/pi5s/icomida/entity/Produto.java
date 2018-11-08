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
@Table(name = "produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")
    , @NamedQuery(name = "Produto.findByProdutoId", query = "SELECT p FROM Produto p WHERE p.produtoId = :produtoId")
    , @NamedQuery(name = "Produto.findByProdutoCodigo", query = "SELECT p FROM Produto p WHERE p.produtoCodigo = :produtoCodigo")
    , @NamedQuery(name = "Produto.findByProdutoNome", query = "SELECT p FROM Produto p WHERE p.produtoNome = :produtoNome")})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "produto_id")
    private Integer produtoId;
    @Column(name = "produto_codigo")
    private Integer produtoCodigo;
    @Size(max = 150)
    @Column(name = "produto_nome")
    private String produtoNome;
    @OneToMany(mappedBy = "produtoId")
    private Collection<Pedidoitem> pedidoitemCollection;
    @JoinColumn(name = "empresa_id", referencedColumnName = "empresa_id")
    @ManyToOne
    private Empresa empresaId;
    @JoinColumn(name = "stts_id", referencedColumnName = "stts_id")
    @ManyToOne
    private Stts sttsId;
    @OneToMany(mappedBy = "produtoId")
    private Collection<Produtovenda> produtovendaCollection;

    public Produto() {
    }

    public Produto(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getProdutoCodigo() {
        return produtoCodigo;
    }

    public void setProdutoCodigo(Integer produtoCodigo) {
        this.produtoCodigo = produtoCodigo;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    @XmlTransient
    public Collection<Pedidoitem> getPedidoitemCollection() {
        return pedidoitemCollection;
    }

    public void setPedidoitemCollection(Collection<Pedidoitem> pedidoitemCollection) {
        this.pedidoitemCollection = pedidoitemCollection;
    }

    public Empresa getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Empresa empresaId) {
        this.empresaId = empresaId;
    }

    public Stts getSttsId() {
        return sttsId;
    }

    public void setSttsId(Stts sttsId) {
        this.sttsId = sttsId;
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
        hash += (produtoId != null ? produtoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.produtoId == null && other.produtoId != null) || (this.produtoId != null && !this.produtoId.equals(other.produtoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Produto[ produtoId=" + produtoId + " ]";
    }
    
}
