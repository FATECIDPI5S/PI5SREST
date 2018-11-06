package br.com.pi5s.icomida.entity;

import br.com.pi5s.icomida.entity.Empresa;
import br.com.pi5s.icomida.entity.Mesa;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-06T00:30:03")
@StaticMetamodel(Ambiente.class)
public class Ambiente_ { 

    public static volatile SingularAttribute<Ambiente, Empresa> empresaId;
    public static volatile CollectionAttribute<Ambiente, Mesa> mesaCollection;
    public static volatile SingularAttribute<Ambiente, Integer> ambienteOrdem;
    public static volatile SingularAttribute<Ambiente, Integer> ambienteId;
    public static volatile SingularAttribute<Ambiente, String> ambienteNome;

}