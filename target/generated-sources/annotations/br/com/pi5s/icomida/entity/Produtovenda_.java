package br.com.pi5s.icomida.entity;

import br.com.pi5s.icomida.entity.Produto;
import br.com.pi5s.icomida.entity.Stts;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-06T00:30:03")
@StaticMetamodel(Produtovenda.class)
public class Produtovenda_ { 

    public static volatile SingularAttribute<Produtovenda, Produto> produtoId;
    public static volatile SingularAttribute<Produtovenda, Integer> produtovendaId;
    public static volatile SingularAttribute<Produtovenda, Stts> sttsId;
    public static volatile SingularAttribute<Produtovenda, BigDecimal> produtovendaPreco;

}