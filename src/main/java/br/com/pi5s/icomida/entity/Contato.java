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
@Table(name = "contato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contato.findAll", query = "SELECT c FROM Contato c")
    , @NamedQuery(name = "Contato.findByContatoId", query = "SELECT c FROM Contato c WHERE c.contatoId = :contatoId")
    , @NamedQuery(name = "Contato.findByContatoTitulo", query = "SELECT c FROM Contato c WHERE c.contatoTitulo = :contatoTitulo")
    , @NamedQuery(name = "Contato.findByContatoDescricao", query = "SELECT c FROM Contato c WHERE c.contatoDescricao = :contatoDescricao")})
public class Contato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contato_id")
    private Integer contatoId;
    @Size(max = 50)
    @Column(name = "contato_titulo")
    private String contatoTitulo;
    @Size(max = 50)
    @Column(name = "contato_descricao")
    private String contatoDescricao;
    @OneToMany(mappedBy = "contatoId")
    private Collection<Funcionariocontato> funcionariocontatoCollection;
    @OneToMany(mappedBy = "contatoId")
    private Collection<Empresacontato> empresacontatoCollection;
    @JoinColumn(name = "contatotipo_id", referencedColumnName = "contatotipo_id")
    @ManyToOne
    private Contatotipo contatotipoId;

    public Contato() {
    }

    public Contato(Integer contatoId) {
        this.contatoId = contatoId;
    }

    public Integer getContatoId() {
        return contatoId;
    }

    public void setContatoId(Integer contatoId) {
        this.contatoId = contatoId;
    }

    public String getContatoTitulo() {
        return contatoTitulo;
    }

    public void setContatoTitulo(String contatoTitulo) {
        this.contatoTitulo = contatoTitulo;
    }

    public String getContatoDescricao() {
        return contatoDescricao;
    }

    public void setContatoDescricao(String contatoDescricao) {
        this.contatoDescricao = contatoDescricao;
    }

    @XmlTransient
    public Collection<Funcionariocontato> getFuncionariocontatoCollection() {
        return funcionariocontatoCollection;
    }

    public void setFuncionariocontatoCollection(Collection<Funcionariocontato> funcionariocontatoCollection) {
        this.funcionariocontatoCollection = funcionariocontatoCollection;
    }

    @XmlTransient
    public Collection<Empresacontato> getEmpresacontatoCollection() {
        return empresacontatoCollection;
    }

    public void setEmpresacontatoCollection(Collection<Empresacontato> empresacontatoCollection) {
        this.empresacontatoCollection = empresacontatoCollection;
    }

    public Contatotipo getContatotipoId() {
        return contatotipoId;
    }

    public void setContatotipoId(Contatotipo contatotipoId) {
        this.contatotipoId = contatotipoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contatoId != null ? contatoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contato)) {
            return false;
        }
        Contato other = (Contato) object;
        if ((this.contatoId == null && other.contatoId != null) || (this.contatoId != null && !this.contatoId.equals(other.contatoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Contato[ contatoId=" + contatoId + " ]";
    }

}
