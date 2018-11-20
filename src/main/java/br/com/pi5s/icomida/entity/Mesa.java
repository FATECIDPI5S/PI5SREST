package br.com.pi5s.icomida.entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Guilherme Abacherli
 */
@Entity
@Table(name = "mesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesa.findAll", query = "SELECT m FROM Mesa m")
    , @NamedQuery(name = "Mesa.findByMesaId", query = "SELECT m FROM Mesa m WHERE m.mesaId = :mesaId")
    , @NamedQuery(name = "Mesa.findByMesaIdentificacao", query = "SELECT m FROM Mesa m WHERE m.mesaIdentificacao = :mesaIdentificacao")})
public class Mesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mesa_id")
    private Integer mesaId;
    @Column(name = "mesa_identificacao")
    private Integer mesaIdentificacao;
    @JoinColumn(name = "ambiente_id", referencedColumnName = "ambiente_id")
    @ManyToOne
    private Ambiente ambienteId;
    @OneToMany(mappedBy = "mesaId")
    private Collection<Pedido> pedidoCollection;

    public Mesa() {
    }

    public Mesa(Integer mesaId) {
        this.mesaId = mesaId;
    }

    public Integer getMesaId() {
        return mesaId;
    }

    public void setMesaId(Integer mesaId) {
        this.mesaId = mesaId;
    }

    public Integer getMesaIdentificacao() {
        return mesaIdentificacao;
    }

    public void setMesaIdentificacao(Integer mesaIdentificacao) {
        this.mesaIdentificacao = mesaIdentificacao;
    }

    public Ambiente getAmbienteId() {
        return ambienteId;
    }

    public void setAmbienteId(Ambiente ambienteId) {
        this.ambienteId = ambienteId;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mesaId != null ? mesaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesa)) {
            return false;
        }
        Mesa other = (Mesa) object;
        if ((this.mesaId == null && other.mesaId != null) || (this.mesaId != null && !this.mesaId.equals(other.mesaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Mesa[ mesaId=" + mesaId + " ]";
    }
}