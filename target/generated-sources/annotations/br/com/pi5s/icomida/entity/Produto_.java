package br.com.pi5s.icomida.entity;

import br.com.pi5s.icomida.entity.Empresa;
import br.com.pi5s.icomida.entity.Pedidoitem;
import br.com.pi5s.icomida.entity.Produtovenda;
import br.com.pi5s.icomida.entity.Stts;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-06T00:30:03")
@StaticMetamodel(Produto.class)
public class Produto_ { 

    public static volatile CollectionAttribute<Produto, Produtovenda> produtovendaCollection;
    public static volatile SingularAttribute<Produto, Integer> produtoId;
    public static volatile SingularAttribute<Produto, String> produtoNome;
    public static volatile SingularAttribute<Produto, Empresa> empresaId;
    public static volatile CollectionAttribute<Produto, Pedidoitem> pedidoitemCollection;
    public static volatile SingularAttribute<Produto, Integer> produtoCodigo;
    public static volatile SingularAttribute<Produto, Stts> sttsId;

}