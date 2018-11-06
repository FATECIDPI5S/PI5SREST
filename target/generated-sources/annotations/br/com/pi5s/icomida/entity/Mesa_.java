package br.com.pi5s.icomida.entity;

import br.com.pi5s.icomida.entity.Ambiente;
import br.com.pi5s.icomida.entity.Pedido;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-06T00:30:03")
@StaticMetamodel(Mesa.class)
public class Mesa_ { 

    public static volatile SingularAttribute<Mesa, Integer> mesaId;
    public static volatile SingularAttribute<Mesa, Integer> mesaIdentificacao;
    public static volatile CollectionAttribute<Mesa, Pedido> pedidoCollection;
    public static volatile SingularAttribute<Mesa, Ambiente> ambienteId;

}