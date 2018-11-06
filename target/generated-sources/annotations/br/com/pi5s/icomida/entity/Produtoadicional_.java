package br.com.pi5s.icomida.entity;

import br.com.pi5s.icomida.entity.Pedidoitemadicional;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-06T00:30:03")
@StaticMetamodel(Produtoadicional.class)
public class Produtoadicional_ { 

    public static volatile CollectionAttribute<Produtoadicional, Pedidoitemadicional> pedidoitemadicionalCollection;
    public static volatile SingularAttribute<Produtoadicional, Integer> produtoadicionalCodigo;
    public static volatile SingularAttribute<Produtoadicional, BigDecimal> produtoadicionalPrecoVenda;
    public static volatile SingularAttribute<Produtoadicional, Integer> produtoadicionalId;
    public static volatile SingularAttribute<Produtoadicional, String> produtoadicionalDescricao;

}