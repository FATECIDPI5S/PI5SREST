/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi5s.icomida.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Guilherme Abacherli
 */
@Entity
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
    , @NamedQuery(name = "Pedido.findByPedidoId", query = "SELECT p FROM Pedido p WHERE p.pedidoId = :pedidoId")
    , @NamedQuery(name = "Pedido.findByPedidoData", query = "SELECT p FROM Pedido p WHERE p.pedidoData = :pedidoData")
    , @NamedQuery(name = "Pedido.findByPedidoSubtotal", query = "SELECT p FROM Pedido p WHERE p.pedidoSubtotal = :pedidoSubtotal")
    , @NamedQuery(name = "Pedido.findByPedidoTaxaGarcom", query = "SELECT p FROM Pedido p WHERE p.pedidoTaxaGarcom = :pedidoTaxaGarcom")
    , @NamedQuery(name = "Pedido.findByPedidoValorTotal", query = "SELECT p FROM Pedido p WHERE p.pedidoValorTotal = :pedidoValorTotal")
    , @NamedQuery(name = "Pedido.findBySttsId", query = "SELECT p FROM Pedido p WHERE p.sttsId = :sttsId")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pedido_id")
    private Integer pedidoId;
    @Column(name = "pedido_data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pedidoData;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pedido_subtotal")
    private BigDecimal pedidoSubtotal;
    @Column(name = "pedido_taxa_garcom")
    private BigDecimal pedidoTaxaGarcom;
    @Column(name = "pedido_valor_total")
    private BigDecimal pedidoValorTotal;
    @Column(name = "stts_id")
    private Integer sttsId;
    @OneToMany(mappedBy = "pedidoId")
    private Collection<Pedidoitem> pedidoitemCollection;
    @JoinColumn(name = "empresa_id", referencedColumnName = "empresa_id")
    @ManyToOne
    private Empresa empresaId;
    @JoinColumn(name = "funcionario_id", referencedColumnName = "funcionario_id")
    @ManyToOne
    private Funcionario funcionarioId;
    @JoinColumn(name = "mesa_id", referencedColumnName = "mesa_id")
    @ManyToOne
    private Mesa mesaId;

    public Pedido() {
    }

    public Pedido(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Date getPedidoData() {
        return pedidoData;
    }

    public void setPedidoData(Date pedidoData) {
        this.pedidoData = pedidoData;
    }

    public BigDecimal getPedidoSubtotal() {
        return pedidoSubtotal;
    }

    public void setPedidoSubtotal(BigDecimal pedidoSubtotal) {
        this.pedidoSubtotal = pedidoSubtotal;
    }

    public BigDecimal getPedidoTaxaGarcom() {
        return pedidoTaxaGarcom;
    }

    public void setPedidoTaxaGarcom(BigDecimal pedidoTaxaGarcom) {
        this.pedidoTaxaGarcom = pedidoTaxaGarcom;
    }

    public BigDecimal getPedidoValorTotal() {
        return pedidoValorTotal;
    }

    public void setPedidoValorTotal(BigDecimal pedidoValorTotal) {
        this.pedidoValorTotal = pedidoValorTotal;
    }

    public Integer getSttsId() {
        return sttsId;
    }

    public void setSttsId(Integer sttsId) {
        this.sttsId = sttsId;
    }

    @XmlTransient
    public Collection<Pedidoitem> getPedidoitemCollection() {
        return pedidoitemCollection;
    }

    public void setPedidoitemCollection(Collection<Pedidoitem> pedidoitemCollection) {
        this.pedidoitemCollection = pedidoitemCollection;
    }

    public Empresa getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Empresa empresaId) {
        this.empresaId = empresaId;
    }

    public Funcionario getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Funcionario funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public Mesa getMesaId() {
        return mesaId;
    }

    public void setMesaId(Mesa mesaId) {
        this.mesaId = mesaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidoId != null ? pedidoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.pedidoId == null && other.pedidoId != null) || (this.pedidoId != null && !this.pedidoId.equals(other.pedidoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi5s.icomida.entity.Pedido[ pedidoId=" + pedidoId + " ]";
    }
    
}
