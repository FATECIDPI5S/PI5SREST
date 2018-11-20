package br.com.pi5s.icomida.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Guilherme Abacherli
 */
@Entity
@Table(name = "produtovenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produtovenda.findAll", query = "SELECT p FROM Produtovenda p")
    , @NamedQuery(name = "Produtovenda.findByProdutovendaId", query = "SELECT p FROM Produtovenda p WHERE p.produtovendaId = :produtovendaId")
    , @NamedQuery(name = "Produtovenda.findByProdutovendaPreco", query = "SELECT p FROM Produtovenda p WHERE p.produtovendaPreco = :produtovendaPreco")})
public class Produtovenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produtovenda_id")
    private Integer produtovendaId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "produtovenda_preco")
    private BigDecimal produtovendaPreco;
    @JoinColumn(name = "produto_id", referencedColumnName = "produto_id")
    @ManyToOne
    private Produto produtoId;
    @JoinColumn(name = "stts_id", referencedColumnName = "stts_id")
    @ManyToOne
    private Stts sttsId;

    public Produtovenda() {
    }

    public Produtovenda(Integer produtovendaId) {
        this.produtovendaId = produtovendaId;
    }

    public Integer getProdutovendaId() {
        return produtovendaId;
    }

    public void setProdutovendaId(Integer produtovendaId) {
        this.produtovendaId = produtovendaId;
    }

    public BigDecimal getProdutovendaPreco() {
        return produtovendaPreco;
    }

    public void setProdutovendaPreco(BigDecimal produtovendaPreco) {
        this.produtovendaPreco = produtovendaPreco;
    }

    public Produto getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Produto produtoId) {
        this.produtoId = produtoId;
    }

    public Stts getSttsId() {
        return sttsId;
    }

    public void setSttsId(Stts sttsId) {
        this.sttsId = sttsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (produtovendaId != null ? produtovendaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtovenda)) {
            return false;
        }
        Produtovenda other = (Produtovenda) object;
        if ((this.produtovendaId == null && other.produtovendaId != null) || (this.produtovendaId != null && !this.produtovendaId.equals(other.produtovendaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Produtovenda[ produtovendaId=" + produtovendaId + " ]";
    }
}