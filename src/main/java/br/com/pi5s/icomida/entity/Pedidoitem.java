/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi5s.icomida.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "pedidoitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidoitem.findAll", query = "SELECT p FROM Pedidoitem p")
    , @NamedQuery(name = "Pedidoitem.findByPedidoitemId", query = "SELECT p FROM Pedidoitem p WHERE p.pedidoitemId = :pedidoitemId")
    , @NamedQuery(name = "Pedidoitem.findByPedidoitemQuantidade", query = "SELECT p FROM Pedidoitem p WHERE p.pedidoitemQuantidade = :pedidoitemQuantidade")
    , @NamedQuery(name = "Pedidoitem.findByPedidoitemPrecoVenda", query = "SELECT p FROM Pedidoitem p WHERE p.pedidoitemPrecoVenda = :pedidoitemPrecoVenda")
    , @NamedQuery(name = "Pedidoitem.findByPedidoitemValorTotal", query = "SELECT p FROM Pedidoitem p WHERE p.pedidoitemValorTotal = :pedidoitemValorTotal")
    , @NamedQuery(name = "Pedidoitem.findByPedidoitemObservacao", query = "SELECT p FROM Pedidoitem p WHERE p.pedidoitemObservacao = :pedidoitemObservacao")})
public class Pedidoitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pedidoitem_id")
    private Integer pedidoitemId;
    @Column(name = "pedidoitem_quantidade")
    private Integer pedidoitemQuantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pedidoitem_preco_venda")
    private BigDecimal pedidoitemPrecoVenda;
    @Column(name = "pedidoitem_valor_total")
    private BigDecimal pedidoitemValorTotal;
    @Size(max = 255)
    @Column(name = "pedidoitem_observacao")
    private String pedidoitemObservacao;
    @JoinColumn(name = "pedido_id", referencedColumnName = "pedido_id")
    @ManyToOne
    private Pedido pedidoId;
    @JoinColumn(name = "produto_id", referencedColumnName = "produto_id")
    @ManyToOne
    private Produto produtoId;
    @OneToMany(mappedBy = "pedidoitemId")
    private Collection<Pedidoitemadicional> pedidoitemadicionalCollection;

    public Pedidoitem() {
    }

    public Pedidoitem(Integer pedidoitemId) {
        this.pedidoitemId = pedidoitemId;
    }

    public Integer getPedidoitemId() {
        return pedidoitemId;
    }

    public void setPedidoitemId(Integer pedidoitemId) {
        this.pedidoitemId = pedidoitemId;
    }

    public Integer getPedidoitemQuantidade() {
        return pedidoitemQuantidade;
    }

    public void setPedidoitemQuantidade(Integer pedidoitemQuantidade) {
        this.pedidoitemQuantidade = pedidoitemQuantidade;
    }

    public BigDecimal getPedidoitemPrecoVenda() {
        return pedidoitemPrecoVenda;
    }

    public void setPedidoitemPrecoVenda(BigDecimal pedidoitemPrecoVenda) {
        this.pedidoitemPrecoVenda = pedidoitemPrecoVenda;
    }

    public BigDecimal getPedidoitemValorTotal() {
        return pedidoitemValorTotal;
    }

    public void setPedidoitemValorTotal(BigDecimal pedidoitemValorTotal) {
        this.pedidoitemValorTotal = pedidoitemValorTotal;
    }

    public String getPedidoitemObservacao() {
        return pedidoitemObservacao;
    }

    public void setPedidoitemObservacao(String pedidoitemObservacao) {
        this.pedidoitemObservacao = pedidoitemObservacao;
    }

    public Pedido getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Pedido pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Produto getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Produto produtoId) {
        this.produtoId = produtoId;
    }

    @XmlTransient
    public Collection<Pedidoitemadicional> getPedidoitemadicionalCollection() {
        return pedidoitemadicionalCollection;
    }

    public void setPedidoitemadicionalCollection(Collection<Pedidoitemadicional> pedidoitemadicionalCollection) {
        this.pedidoitemadicionalCollection = pedidoitemadicionalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidoitemId != null ? pedidoitemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidoitem)) {
            return false;
        }
        Pedidoitem other = (Pedidoitem) object;
        if ((this.pedidoitemId == null && other.pedidoitemId != null) || (this.pedidoitemId != null && !this.pedidoitemId.equals(other.pedidoitemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Pedidoitem[ pedidoitemId=" + pedidoitemId + " ]";
    }
    
}
