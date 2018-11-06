package br.com.pi5s.icomida.entity;

import br.com.pi5s.icomida.entity.Endereco;
import br.com.pi5s.icomida.entity.Estado;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-06T00:30:03")
@StaticMetamodel(Cidade.class)
public class Cidade_ { 

    public static volatile SingularAttribute<Cidade, Integer> cidadeId;
    public static volatile CollectionAttribute<Cidade, Endereco> enderecoCollection;
    public static volatile SingularAttribute<Cidade, Estado> estadoId;
    public static volatile SingularAttribute<Cidade, String> cidadeNome;

}