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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Guilherme Abacherli
 */
@Entity
@Table(name = "cidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cidade.findAll", query = "SELECT c FROM Cidade c")
    , @NamedQuery(name = "Cidade.findByCidadeId", query = "SELECT c FROM Cidade c WHERE c.cidadeId = :cidadeId")
    , @NamedQuery(name = "Cidade.findByCidadeNome", query = "SELECT c FROM Cidade c WHERE c.cidadeNome = :cidadeNome")})
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cidade_id")
    private Integer cidadeId;
    @Size(max = 255)
    @Column(name = "cidade_nome")
    private String cidadeNome;
    @JoinColumn(name = "estado_id", referencedColumnName = "estado_id")
    @ManyToOne
    private Estado estadoId;
    @OneToMany(mappedBy = "cidadeId")
    private Collection<Endereco> enderecoCollection;

    public Cidade() {
    }

    public Cidade(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }

    public String getCidadeNome() {
        return cidadeNome;
    }

    public void setCidadeNome(String cidadeNome) {
        this.cidadeNome = cidadeNome;
    }

    public Estado getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Estado estadoId) {
        this.estadoId = estadoId;
    }

    @XmlTransient
    public Collection<Endereco> getEnderecoCollection() {
        return enderecoCollection;
    }

    public void setEnderecoCollection(Collection<Endereco> enderecoCollection) {
        this.enderecoCollection = enderecoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cidadeId != null ? cidadeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cidade)) {
            return false;
        }
        Cidade other = (Cidade) object;
        if ((this.cidadeId == null && other.cidadeId != null) || (this.cidadeId != null && !this.cidadeId.equals(other.cidadeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Cidade[ cidadeId=" + cidadeId + " ]";
    }

}
