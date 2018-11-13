/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi5s.icomida.view;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Guilherme Abacherli
 */
@Entity
@Table(name = "PedidoPendenteCozinha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidoPendenteCozinha.findAll", query = "SELECT p FROM PedidoPendenteCozinha p")
    , @NamedQuery(name = "PedidoPendenteCozinha.findByPedido", query = "SELECT p FROM PedidoPendenteCozinha p WHERE p.pedido = :pedido")
    , @NamedQuery(name = "PedidoPendenteCozinha.findByQtd", query = "SELECT p FROM PedidoPendenteCozinha p WHERE p.qtd = :qtd")
    , @NamedQuery(name = "PedidoPendenteCozinha.findByProduto", query = "SELECT p FROM PedidoPendenteCozinha p WHERE p.produto = :produto")
    , @NamedQuery(name = "PedidoPendenteCozinha.findByObs", query = "SELECT p FROM PedidoPendenteCozinha p WHERE p.obs = :obs")})
public class PedidoPendenteCozinha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 30)
    @Column(name = "pedido")
    @Id
    private String pedido;
    @Size(max = 30)
    @Column(name = "qtd")
    private String qtd;
    @Size(max = 30)
    @Column(name = "produto")
    private String produto;
    @Size(max = 30)
    @Column(name = "obs")
    private String obs;

    public PedidoPendenteCozinha() {
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

}
