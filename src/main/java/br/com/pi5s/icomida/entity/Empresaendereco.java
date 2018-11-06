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
@Table(name = "empresaendereco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresaendereco.findAll", query = "SELECT e FROM Empresaendereco e")
    , @NamedQuery(name = "Empresaendereco.findByEmpresaenderecoId", query = "SELECT e FROM Empresaendereco e WHERE e.empresaenderecoId = :empresaenderecoId")})
public class Empresaendereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresaendereco_id")
    private Integer empresaenderecoId;
    @JoinColumn(name = "empresa_id", referencedColumnName = "empresa_id")
    @ManyToOne
    private Empresa empresaId;
    @JoinColumn(name = "endereco_id", referencedColumnName = "endereco_id")
    @ManyToOne
    private Endereco enderecoId;

    public Empresaendereco() {
    }

    public Empresaendereco(Integer empresaenderecoId) {
        this.empresaenderecoId = empresaenderecoId;
    }

    public Integer getEmpresaenderecoId() {
        return empresaenderecoId;
    }

    public void setEmpresaenderecoId(Integer empresaenderecoId) {
        this.empresaenderecoId = empresaenderecoId;
    }

    public Empresa getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Empresa empresaId) {
        this.empresaId = empresaId;
    }

    public Endereco getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Endereco enderecoId) {
        this.enderecoId = enderecoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empresaenderecoId != null ? empresaenderecoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresaendereco)) {
            return false;
        }
        Empresaendereco other = (Empresaendereco) object;
        if ((this.empresaenderecoId == null && other.empresaenderecoId != null) || (this.empresaenderecoId != null && !this.empresaenderecoId.equals(other.empresaenderecoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Empresaendereco[ empresaenderecoId=" + empresaenderecoId + " ]";
    }

}
