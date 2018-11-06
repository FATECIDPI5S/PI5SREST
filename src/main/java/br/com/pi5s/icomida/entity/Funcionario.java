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
@Table(name = "funcionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f")
    , @NamedQuery(name = "Funcionario.findByFuncionarioId", query = "SELECT f FROM Funcionario f WHERE f.funcionarioId = :funcionarioId")
    , @NamedQuery(name = "Funcionario.findByFuncionarioCpf", query = "SELECT f FROM Funcionario f WHERE f.funcionarioCpf = :funcionarioCpf")
    , @NamedQuery(name = "Funcionario.findByFuncionarioRg", query = "SELECT f FROM Funcionario f WHERE f.funcionarioRg = :funcionarioRg")
    , @NamedQuery(name = "Funcionario.findByFuncionarioNome", query = "SELECT f FROM Funcionario f WHERE f.funcionarioNome = :funcionarioNome")
    , @NamedQuery(name = "Funcionario.findByFuncionarioApelido", query = "SELECT f FROM Funcionario f WHERE f.funcionarioApelido = :funcionarioApelido")
    , @NamedQuery(name = "Funcionario.findByFuncionarioUsuario", query = "SELECT f FROM Funcionario f WHERE f.funcionarioUsuario = :funcionarioUsuario")
    , @NamedQuery(name = "Funcionario.findByFuncionarioSenha", query = "SELECT f FROM Funcionario f WHERE f.funcionarioSenha = :funcionarioSenha")})
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id")
    private Integer funcionarioId;
    @Size(max = 15)
    @Column(name = "funcionario_cpf")
    private String funcionarioCpf;
    @Size(max = 15)
    @Column(name = "funcionario_rg")
    private String funcionarioRg;
    @Size(max = 255)
    @Column(name = "funcionario_nome")
    private String funcionarioNome;
    @Size(max = 50)
    @Column(name = "funcionario_apelido")
    private String funcionarioApelido;
    @Size(max = 20)
    @Column(name = "funcionario_usuario")
    private String funcionarioUsuario;
    @Size(max = 20)
    @Column(name = "funcionario_senha")
    private String funcionarioSenha;
    @OneToMany(mappedBy = "funcionarioId")
    private Collection<Funcionariocontato> funcionariocontatoCollection;
    @OneToMany(mappedBy = "funcionarioId")
    private Collection<Funcionarioendereco> funcionarioenderecoCollection;
    @OneToMany(mappedBy = "funcionarioId")
    private Collection<Pedido> pedidoCollection;
    @JoinColumn(name = "empresa_id", referencedColumnName = "empresa_id")
    @ManyToOne
    private Empresa empresaId;
    @JoinColumn(name = "stts_id", referencedColumnName = "stts_id")
    @ManyToOne
    private Stts sttsId;

    public Funcionario() {
    }

    public Funcionario(Integer funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public Integer getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Integer funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getFuncionarioCpf() {
        return funcionarioCpf;
    }

    public void setFuncionarioCpf(String funcionarioCpf) {
        this.funcionarioCpf = funcionarioCpf;
    }

    public String getFuncionarioRg() {
        return funcionarioRg;
    }

    public void setFuncionarioRg(String funcionarioRg) {
        this.funcionarioRg = funcionarioRg;
    }

    public String getFuncionarioNome() {
        return funcionarioNome;
    }

    public void setFuncionarioNome(String funcionarioNome) {
        this.funcionarioNome = funcionarioNome;
    }

    public String getFuncionarioApelido() {
        return funcionarioApelido;
    }

    public void setFuncionarioApelido(String funcionarioApelido) {
        this.funcionarioApelido = funcionarioApelido;
    }

    public String getFuncionarioUsuario() {
        return funcionarioUsuario;
    }

    public void setFuncionarioUsuario(String funcionarioUsuario) {
        this.funcionarioUsuario = funcionarioUsuario;
    }

    public String getFuncionarioSenha() {
        return funcionarioSenha;
    }

    public void setFuncionarioSenha(String funcionarioSenha) {
        this.funcionarioSenha = funcionarioSenha;
    }

    @XmlTransient
    public Collection<Funcionariocontato> getFuncionariocontatoCollection() {
        return funcionariocontatoCollection;
    }

    public void setFuncionariocontatoCollection(Collection<Funcionariocontato> funcionariocontatoCollection) {
        this.funcionariocontatoCollection = funcionariocontatoCollection;
    }

    @XmlTransient
    public Collection<Funcionarioendereco> getFuncionarioenderecoCollection() {
        return funcionarioenderecoCollection;
    }

    public void setFuncionarioenderecoCollection(Collection<Funcionarioendereco> funcionarioenderecoCollection) {
        this.funcionarioenderecoCollection = funcionarioenderecoCollection;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (funcionarioId != null ? funcionarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.funcionarioId == null && other.funcionarioId != null) || (this.funcionarioId != null && !this.funcionarioId.equals(other.funcionarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Funcionario[ funcionarioId=" + funcionarioId + " ]";
    }

}
