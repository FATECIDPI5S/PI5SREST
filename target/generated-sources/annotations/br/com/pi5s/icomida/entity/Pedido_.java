package br.com.pi5s.icomida.entity;

import br.com.pi5s.icomida.entity.Empresa;
import br.com.pi5s.icomida.entity.Funcionario;
import br.com.pi5s.icomida.entity.Mesa;
import br.com.pi5s.icomida.entity.Pedidoitem;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-06T00:30:03")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile SingularAttribute<Pedido, BigDecimal> pedidoTaxaGarcom;
    public static volatile SingularAttribute<Pedido, Empresa> empresaId;
    public static volatile SingularAttribute<Pedido, Integer> pedidoId;
    public static volatile SingularAttribute<Pedido, Mesa> mesaId;
    public static volatile SingularAttribute<Pedido, Funcionario> funcionarioId;
    public static volatile CollectionAttribute<Pedido, Pedidoitem> pedidoitemCollection;
    public static volatile SingularAttribute<Pedido, Date> pedidoData;
    public static volatile SingularAttribute<Pedido, BigDecimal> pedidoValorTotal;
    public static volatile SingularAttribute<Pedido, BigDecimal> pedidoSubtotal;
    public static volatile SingularAttribute<Pedido, Integer> sttsId;

}