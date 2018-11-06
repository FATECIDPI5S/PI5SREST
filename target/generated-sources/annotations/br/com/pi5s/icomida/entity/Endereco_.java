package br.com.pi5s.icomida.entity;

import br.com.pi5s.icomida.entity.Cidade;
import br.com.pi5s.icomida.entity.Empresaendereco;
import br.com.pi5s.icomida.entity.Enderecotipo;
import br.com.pi5s.icomida.entity.Funcionarioendereco;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-06T00:30:03")
@StaticMetamodel(Endereco.class)
public class Endereco_ { 

    public static volatile SingularAttribute<Endereco, String> enderecoLogradouro;
    public static volatile SingularAttribute<Endereco, Cidade> cidadeId;
    public static volatile SingularAttribute<Endereco, String> enderecoNumero;
    public static volatile SingularAttribute<Endereco, Integer> enderecoCep;
    public static volatile CollectionAttribute<Endereco, Empresaendereco> empresaenderecoCollection;
    public static volatile SingularAttribute<Endereco, Integer> enderecoId;
    public static volatile CollectionAttribute<Endereco, Funcionarioendereco> funcionarioenderecoCollection;
    public static volatile SingularAttribute<Endereco, String> enderecoTipoLogradouro;
    public static volatile SingularAttribute<Endereco, String> enderecoBairro;
    public static volatile SingularAttribute<Endereco, Enderecotipo> enderecotipoId;

}