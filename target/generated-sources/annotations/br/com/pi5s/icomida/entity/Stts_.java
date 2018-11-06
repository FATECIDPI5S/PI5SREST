package br.com.pi5s.icomida.entity;

import br.com.pi5s.icomida.entity.Funcionario;
import br.com.pi5s.icomida.entity.Produto;
import br.com.pi5s.icomida.entity.Produtovenda;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-06T00:30:03")
@StaticMetamodel(Stts.class)
public class Stts_ { 

    public static volatile CollectionAttribute<Stts, Produtovenda> produtovendaCollection;
    public static volatile CollectionAttribute<Stts, Funcionario> funcionarioCollection;
    public static volatile SingularAttribute<Stts, String> sttsDescricao;
    public static volatile CollectionAttribute<Stts, Produto> produtoCollection;
    public static volatile SingularAttribute<Stts, Integer> sttsId;

}