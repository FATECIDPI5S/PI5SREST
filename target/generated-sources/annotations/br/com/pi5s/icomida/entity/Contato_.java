package br.com.pi5s.icomida.entity;

import br.com.pi5s.icomida.entity.Contatotipo;
import br.com.pi5s.icomida.entity.Empresacontato;
import br.com.pi5s.icomida.entity.Funcionariocontato;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-06T00:30:03")
@StaticMetamodel(Contato.class)
public class Contato_ { 

    public static volatile SingularAttribute<Contato, Integer> contatoId;
    public static volatile SingularAttribute<Contato, String> contatoDescricao;
    public static volatile CollectionAttribute<Contato, Funcionariocontato> funcionariocontatoCollection;
    public static volatile SingularAttribute<Contato, String> contatoTitulo;
    public static volatile CollectionAttribute<Contato, Empresacontato> empresacontatoCollection;
    public static volatile SingularAttribute<Contato, Contatotipo> contatotipoId;

}