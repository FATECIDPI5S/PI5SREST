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
@Table(name = "endereco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e")
    , @NamedQuery(name = "Endereco.findByEnderecoId", query = "SELECT e FROM Endereco e WHERE e.enderecoId = :enderecoId")
    , @NamedQuery(name = "Endereco.findByEnderecoTipoLogradouro", query = "SELECT e FROM Endereco e WHERE e.enderecoTipoLogradouro = :enderecoTipoLogradouro")
    , @NamedQuery(name = "Endereco.findByEnderecoLogradouro", query = "SELECT e FROM Endereco e WHERE e.enderecoLogradouro = :enderecoLogradouro")
    , @NamedQuery(name = "Endereco.findByEnderecoNumero", query = "SELECT e FROM Endereco e WHERE e.enderecoNumero = :enderecoNumero")
    , @NamedQuery(name = "Endereco.findByEnderecoBairro", query = "SELECT e FROM Endereco e WHERE e.enderecoBairro = :enderecoBairro")
    , @NamedQuery(name = "Endereco.findByEnderecoCep", query = "SELECT e FROM Endereco e WHERE e.enderecoCep = :enderecoCep")})
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "endereco_id")
    private Integer enderecoId;
    @Size(max = 20)
    @Column(name = "endereco_tipo_logradouro")
    private String enderecoTipoLogradouro;
    @Size(max = 255)
    @Column(name = "endereco_logradouro")
    private String enderecoLogradouro;
    @Size(max = 11)
    @Column(name = "endereco_numero")
    private String enderecoNumero;
    @Size(max = 255)
    @Column(name = "endereco_bairro")
    private String enderecoBairro;
    @Column(name = "endereco_cep")
    private Integer enderecoCep;
    @JoinColumn(name = "cidade_id", referencedColumnName = "cidade_id")
    @ManyToOne
    private Cidade cidadeId;
    @JoinColumn(name = "enderecotipo_id", referencedColumnName = "enderecotipo_id")
    @ManyToOne
    private Enderecotipo enderecotipoId;
    @OneToMany(mappedBy = "enderecoId")
    private Collection<Funcionarioendereco> funcionarioenderecoCollection;
    @OneToMany(mappedBy = "enderecoId")
    private Collection<Empresaendereco> empresaenderecoCollection;

    public Endereco() {
    }

    public Endereco(Integer enderecoId) {
        this.enderecoId = enderecoId;
    }

    public Integer getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Integer enderecoId) {
        this.enderecoId = enderecoId;
    }

    public String getEnderecoTipoLogradouro() {
        return enderecoTipoLogradouro;
    }

    public void setEnderecoTipoLogradouro(String enderecoTipoLogradouro) {
        this.enderecoTipoLogradouro = enderecoTipoLogradouro;
    }

    public String getEnderecoLogradouro() {
        return enderecoLogradouro;
    }

    public void setEnderecoLogradouro(String enderecoLogradouro) {
        this.enderecoLogradouro = enderecoLogradouro;
    }

    public String getEnderecoNumero() {
        return enderecoNumero;
    }

    public void setEnderecoNumero(String enderecoNumero) {
        this.enderecoNumero = enderecoNumero;
    }

    public String getEnderecoBairro() {
        return enderecoBairro;
    }

    public void setEnderecoBairro(String enderecoBairro) {
        this.enderecoBairro = enderecoBairro;
    }

    public Integer getEnderecoCep() {
        return enderecoCep;
    }

    public void setEnderecoCep(Integer enderecoCep) {
        this.enderecoCep = enderecoCep;
    }

    public Cidade getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Cidade cidadeId) {
        this.cidadeId = cidadeId;
    }

    public Enderecotipo getEnderecotipoId() {
        return enderecotipoId;
    }

    public void setEnderecotipoId(Enderecotipo enderecotipoId) {
        this.enderecotipoId = enderecotipoId;
    }

    @XmlTransient
    public Collection<Funcionarioendereco> getFuncionarioenderecoCollection() {
        return funcionarioenderecoCollection;
    }

    public void setFuncionarioenderecoCollection(Collection<Funcionarioendereco> funcionarioenderecoCollection) {
        this.funcionarioenderecoCollection = funcionarioenderecoCollection;
    }

    @XmlTransient
    public Collection<Empresaendereco> getEmpresaenderecoCollection() {
        return empresaenderecoCollection;
    }

    public void setEmpresaenderecoCollection(Collection<Empresaendereco> empresaenderecoCollection) {
        this.empresaenderecoCollection = empresaenderecoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enderecoId != null ? enderecoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.enderecoId == null && other.enderecoId != null) || (this.enderecoId != null && !this.enderecoId.equals(other.enderecoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Endereco[ enderecoId=" + enderecoId + " ]";
    }

}
