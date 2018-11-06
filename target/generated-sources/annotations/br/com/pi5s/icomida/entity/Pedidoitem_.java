package br.com.pi5s.icomida.entity;

import br.com.pi5s.icomida.entity.Pedido;
import br.com.pi5s.icomida.entity.Pedidoitemadicional;
import br.com.pi5s.icomida.entity.Produto;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-06T00:30:03")
@StaticMetamodel(Pedidoitem.class)
public class Pedidoitem_ { 

    public static volatile SingularAttribute<Pedidoitem, Produto> produtoId;
    public static volatile CollectionAttribute<Pedidoitem, Pedidoitemadicional> pedidoitemadicionalCollection;
    public static volatile SingularAttribute<Pedidoitem, Pedido> pedidoId;
    public static volatile SingularAttribute<Pedidoitem, String> pedidoitemObservacao;
    public static volatile SingularAttribute<Pedidoitem, Integer> pedidoitemQuantidade;
    public static volatile SingularAttribute<Pedidoitem, BigDecimal> pedidoitemValorTotal;
    public static volatile SingularAttribute<Pedidoitem, BigDecimal> pedidoitemPrecoVenda;
    public static volatile SingularAttribute<Pedidoitem, Integer> pedidoitemId;

}