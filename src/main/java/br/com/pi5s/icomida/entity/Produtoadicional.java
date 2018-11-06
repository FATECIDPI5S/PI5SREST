package br.com.pi5s.icomida.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Guilherme Abacherli
 */
@Entity
@Table(name = "produtoadicional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produtoadicional.findAll", query = "SELECT p FROM Produtoadicional p")
    , @NamedQuery(name = "Produtoadicional.findByProdutoadicionalId", query = "SELECT p FROM Produtoadicional p WHERE p.produtoadicionalId = :produtoadicionalId")
    , @NamedQuery(name = "Produtoadicional.findByProdutoadicionalCodigo", query = "SELECT p FROM Produtoadicional p WHERE p.produtoadicionalCodigo = :produtoadicionalCodigo")
    , @NamedQuery(name = "Produtoadicional.findByProdutoadicionalDescricao", query = "SELECT p FROM Produtoadicional p WHERE p.produtoadicionalDescricao = :produtoadicionalDescricao")
    , @NamedQuery(name = "Produtoadicional.findByProdutoadicionalPrecoVenda", query = "SELECT p FROM Produtoadicional p WHERE p.produtoadicionalPrecoVenda = :produtoadicionalPrecoVenda")})
public class Produtoadicional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produtoadicional_id")
    private Integer produtoadicionalId;
    @Column(name = "produtoadicional_codigo")
    private Integer produtoadicionalCodigo;
    @Size(max = 255)
    @Column(name = "produtoadicional_descricao")
    private String produtoadicionalDescricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "produtoadicional_preco_venda")
    private BigDecimal produtoadicionalPrecoVenda;
    @OneToMany(mappedBy = "produtoadicionalId")
    private Collection<Pedidoitemadicional> pedidoitemadicionalCollection;

    public Produtoadicional() {
    }

    public Produtoadicional(Integer produtoadicionalId) {
        this.produtoadicionalId = produtoadicionalId;
    }

    public Integer getProdutoadicionalId() {
        return produtoadicionalId;
    }

    public void setProdutoadicionalId(Integer produtoadicionalId) {
        this.produtoadicionalId = produtoadicionalId;
    }

    public Integer getProdutoadicionalCodigo() {
        return produtoadicionalCodigo;
    }

    public void setProdutoadicionalCodigo(Integer produtoadicionalCodigo) {
        this.produtoadicionalCodigo = produtoadicionalCodigo;
    }

    public String getProdutoadicionalDescricao() {
        return produtoadicionalDescricao;
    }

    public void setProdutoadicionalDescricao(String produtoadicionalDescricao) {
        this.produtoadicionalDescricao = produtoadicionalDescricao;
    }

    public BigDecimal getProdutoadicionalPrecoVenda() {
        return produtoadicionalPrecoVenda;
    }

    public void setProdutoadicionalPrecoVenda(BigDecimal produtoadicionalPrecoVenda) {
        this.produtoadicionalPrecoVenda = produtoadicionalPrecoVenda;
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
        hash += (produtoadicionalId != null ? produtoadicionalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtoadicional)) {
            return false;
        }
        Produtoadicional other = (Produtoadicional) object;
        if ((this.produtoadicionalId == null && other.produtoadicionalId != null) || (this.produtoadicionalId != null && !this.produtoadicionalId.equals(other.produtoadicionalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Produtoadicional[ produtoadicionalId=" + produtoadicionalId + " ]";
    }

}
