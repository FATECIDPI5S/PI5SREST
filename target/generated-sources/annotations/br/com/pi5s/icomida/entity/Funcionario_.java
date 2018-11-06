package br.com.pi5s.icomida.entity;

import br.com.pi5s.icomida.entity.Empresa;
import br.com.pi5s.icomida.entity.Funcionariocontato;
import br.com.pi5s.icomida.entity.Funcionarioendereco;
import br.com.pi5s.icomida.entity.Pedido;
import br.com.pi5s.icomida.entity.Stts;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-06T00:30:03")
@StaticMetamodel(Funcionario.class)
public class Funcionario_ { 

    public static volatile SingularAttribute<Funcionario, String> funcionarioRg;
    public static volatile SingularAttribute<Funcionario, Empresa> empresaId;
    public static volatile SingularAttribute<Funcionario, Integer> funcionarioId;
    public static volatile CollectionAttribute<Funcionario, Funcionarioendereco> funcionarioenderecoCollection;
    public static volatile SingularAttribute<Funcionario, String> funcionarioUsuario;
    public static volatile SingularAttribute<Funcionario, String> funcionarioNome;
    public static volatile SingularAttribute<Funcionario, String> funcionarioSenha;
    public static volatile CollectionAttribute<Funcionario, Funcionariocontato> funcionariocontatoCollection;
    public static volatile CollectionAttribute<Funcionario, Pedido> pedidoCollection;
    public static volatile SingularAttribute<Funcionario, String> funcionarioCpf;
    public static volatile SingularAttribute<Funcionario, Stts> sttsId;
    public static volatile SingularAttribute<Funcionario, String> funcionarioApelido;

}