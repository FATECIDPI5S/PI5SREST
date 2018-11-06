package br.com.pi5s.icomida.entity;

import br.com.pi5s.icomida.entity.Ambiente;
import br.com.pi5s.icomida.entity.Empresa;
import br.com.pi5s.icomida.entity.Empresacontato;
import br.com.pi5s.icomida.entity.Empresaendereco;
import br.com.pi5s.icomida.entity.Funcionario;
import br.com.pi5s.icomida.entity.Pedido;
import br.com.pi5s.icomida.entity.Produto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-06T00:30:03")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, Empresa> empresaMatriz;
    public static volatile SingularAttribute<Empresa, String> empresaIe;
    public static volatile SingularAttribute<Empresa, Integer> empresaId;
    public static volatile SingularAttribute<Empresa, String> empresaRazaoSocial;
    public static volatile CollectionAttribute<Empresa, Produto> produtoCollection;
    public static volatile CollectionAttribute<Empresa, Empresacontato> empresacontatoCollection;
    public static volatile SingularAttribute<Empresa, String> empresaCnpj;
    public static volatile CollectionAttribute<Empresa, Empresaendereco> empresaenderecoCollection;
    public static volatile CollectionAttribute<Empresa, Ambiente> ambienteCollection;
    public static volatile CollectionAttribute<Empresa, Funcionario> funcionarioCollection;
    public static volatile CollectionAttribute<Empresa, Empresa> empresaCollection;
    public static volatile SingularAttribute<Empresa, String> empresaNomeFantasia;
    public static volatile CollectionAttribute<Empresa, Pedido> pedidoCollection;

}