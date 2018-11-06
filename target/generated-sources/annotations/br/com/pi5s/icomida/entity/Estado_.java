package br.com.pi5s.icomida.entity;

import br.com.pi5s.icomida.entity.Cidade;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-06T00:30:03")
@StaticMetamodel(Estado.class)
public class Estado_ { 

    public static volatile CollectionAttribute<Estado, Cidade> cidadeCollection;
    public static volatile SingularAttribute<Estado, Integer> estadoId;
    public static volatile SingularAttribute<Estado, String> estadoUf;
    public static volatile SingularAttribute<Estado, String> estadoNome;

}