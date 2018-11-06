package br.com.pi5s.icomida.entity;

import java.io.Serializable;
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
@Table(name = "pedidoitemadicional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidoitemadicional.findAll", query = "SELECT p FROM Pedidoitemadicional p")
    , @NamedQuery(name = "Pedidoitemadicional.findByPedidoitemadicionalId", query = "SELECT p FROM Pedidoitemadicional p WHERE p.pedidoitemadicionalId = :pedidoitemadicionalId")})
public class Pedidoitemadicional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedidoitemadicional_id")
    private Integer pedidoitemadicionalId;
    @JoinColumn(name = "pedidoitem_id", referencedColumnName = "pedidoitem_id")
    @ManyToOne
    private Pedidoitem pedidoitemId;
    @JoinColumn(name = "produtoadicional_id", referencedColumnName = "produtoadicional_id")
    @ManyToOne
    private Produtoadicional produtoadicionalId;

    public Pedidoitemadicional() {
    }

    public Pedidoitemadicional(Integer pedidoitemadicionalId) {
        this.pedidoitemadicionalId = pedidoitemadicionalId;
    }

    public Integer getPedidoitemadicionalId() {
        return pedidoitemadicionalId;
    }

    public void setPedidoitemadicionalId(Integer pedidoitemadicionalId) {
        this.pedidoitemadicionalId = pedidoitemadicionalId;
    }

    public Pedidoitem getPedidoitemId() {
        return pedidoitemId;
    }

    public void setPedidoitemId(Pedidoitem pedidoitemId) {
        this.pedidoitemId = pedidoitemId;
    }

    public Produtoadicional getProdutoadicionalId() {
        return produtoadicionalId;
    }

    public void setProdutoadicionalId(Produtoadicional produtoadicionalId) {
        this.produtoadicionalId = produtoadicionalId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidoitemadicionalId != null ? pedidoitemadicionalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidoitemadicional)) {
            return false;
        }
        Pedidoitemadicional other = (Pedidoitemadicional) object;
        if ((this.pedidoitemadicionalId == null && other.pedidoitemadicionalId != null) || (this.pedidoitemadicionalId != null && !this.pedidoitemadicionalId.equals(other.pedidoitemadicionalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Pedidoitemadicional[ pedidoitemadicionalId=" + pedidoitemadicionalId + " ]";
    }

}
