USE [master]
GO
IF EXISTS (SELECT name FROM sys.databases WHERE name = 'icomida')
BEGIN
    DROP DATABASE [icomida]
END
GO
CREATE DATABASE [icomida]
GO
USE [icomida]
GO
/****** Object:  StoredProcedure [dbo].[spAmbienteInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spAmbienteInserir]
	@ambienteId INT OUTPUT,
	@ambienteNome VARCHAR(45),
    @ambienteOrdem INT,
    @empresaId INT
AS
BEGIN
	IF NOT EXISTS (SELECT empresa_id FROM empresa WHERE empresa_id = @empresaId)
	BEGIN
		RAISERROR(50010, 10, 1);
		RETURN
	END

	IF EXISTS (SELECT ambiente_id FROM ambiente WHERE ambiente_nome = @ambienteNome AND empresa_id = @empresaId)
	BEGIN
		RAISERROR(50019, 10, 1);
		RETURN
	END

	IF EXISTS (SELECT ambiente_id FROM ambiente WHERE ambiente_nome = @ambienteNome AND empresa_id = @empresaId AND ambiente_ordem = @ambienteOrdem)
	BEGIN
		RAISERROR(50020, 10, 1);
		RETURN
	END

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO ambiente
           (ambiente_nome
           ,ambiente_ordem
           ,empresa_id)	
		VALUES
           (@ambienteNome
           ,@ambienteOrdem
           ,@empresaId)

		SET @ambienteId = @@IDENTITY;
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spCidadeInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spCidadeInserir]
	@cidadeNome VARCHAR(255),
	@estadoId INT
AS
BEGIN
	IF NOT EXISTS (SELECT estado_id FROM estado WHERE estado_id = @estadoId)
	BEGIN
		RAISERROR(50006, 10, 1);
		RETURN
	END

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO cidade
			   (cidade_nome
			   ,estado_id)
		 VALUES
           (@cidadeNome
           ,@estadoId)
		
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spContatoInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spContatoInserir]
	@contatoId INT OUTPUT,
	@titulo VARCHAR(50),
	@valor VARCHAR(50),
	@tipoId INT
AS
BEGIN
	IF NOT EXISTS (SELECT contatotipo_id FROM contatotipo WHERE contatotipo_id = @tipoId)
	BEGIN
		RAISERROR(50003, 10, 1);
		RETURN;
	END 

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO contato
			   (contato_titulo
			   ,contato_descricao
			   ,contatotipo_id)
		 VALUES
			   (@titulo
			   ,@valor
			   ,@tipoId)


		SET @contatoId = @@IDENTITY
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spContatoTipoInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spContatoTipoInserir]
	@descricao VARCHAR(50)
AS
BEGIN
	IF EXISTS (SELECT contatotipo_id FROM contatotipo  WHERE contatotipo_descricao = @descricao)
	BEGIN
		RAISERROR(50002, 10, 1);
		RETURN;
	END

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO contatotipo
           (contatotipo_descricao)
		VALUES
           (@descricao)
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spEmpresaContatoInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spEmpresaContatoInserir]
	@empresaId INT,
	@contatoTitulo VARCHAR(50),
	@contatoDescricao VARCHAR(50),
	@contatoTipoId INT
AS
BEGIN
	DECLARE @contatoId INT;
	EXEC spContatoInserir @contatoId OUTPUT, @contatoTitulo, @contatoDescricao, @contatoTipoId;

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO empresacontato
           (empresa_id
           ,contato_id)
		VALUES
           (@empresaId
           ,@contatoId)
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spEmpresaEnderecoInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spEmpresaEnderecoInserir]
	@empresaId INT,
	@enderecoTipoLogradouro VARCHAR(20),
    @enderecoLogradouro VARCHAR(255),
	@enderecoNumero VARCHAR(11),
	@enderecoBairro VARCHAR(255),
	@enderecoCEP INT,
	@enderecoTipoId INT,
    @cidadeId INT
AS
BEGIN
	DECLARE @enderecoId INT;
	EXEC spEnderecoInserir @enderecoId OUTPUT, @enderecoTipoLogradouro, @enderecoLogradouro, @enderecoNumero, @enderecoBairro, @enderecoCEP, @enderecoTipoId, @cidadeId;

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO empresaendereco
           (empresa_id
           ,endereco_id)
		VALUES
           (@empresaId
           ,@enderecoId)
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spEmpresaInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spEmpresaInserir]
	@empresaId INT OUTPUT,
	@empresaRazaoSocial VARCHAR(255),
	@empresaNomeFantasia VARCHAR(255),
	@empresaCnpj VARCHAR(255),
	@empresaIe VARCHAR(255),
	@empresaMatriz INT = NULL
AS
BEGIN
	IF EXISTS (SELECT empresa_id FROM empresa WHERE empresa_cnpj = @empresaCnpj OR empresa_razao_social = @empresaRazaoSocial)
	BEGIN
		RAISERROR(50008, 10, 1);
		RETURN;
	END

	IF @empresaMatriz IS NOT NULL
	BEGIN
		IF NOT EXISTS(SELECT empresa_id FROM empresa WHERE empresa_id = @empresaMatriz)
		BEGIN 
			RAISERROR(50009, 10, 1);
			RETURN;
		END
	END

    SET @empresaCnpj = RTRIM(LTRIM(REPLACE(REPLACE(REPLACE(@empresaCnpj,'.',''),'/',''),'-','')))

    IF LEN(@empresaCnpj) <> 14
    BEGIN
        RAISERROR(50017, 10, 1);
		RETURN;
    END
    ELSE BEGIN
        DECLARE @VerificadorCNPJ INT 
        EXEC spvalidarcnpj @empresaCnpj, @VerificadorCNPJ OUTPUT

        IF @VerificadorCNPJ = 0
        BEGIN
            RAISERROR(50018, 10, 1);
		    RETURN;
        END
    END

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO empresa
           (empresa_razao_social
           ,empresa_nome_fantasia
           ,empresa_cnpj
           ,empresa_ie
           ,empresa_matriz)
		VALUES
           (@empresaRazaoSocial
           ,@empresaNomeFantasia
           ,@empresaCnpj
           ,@empresaIe
           ,@empresaMatriz)
		
		COMMIT TRAN
		SET @empresaId = @@IDENTITY
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spEnderecoInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spEnderecoInserir]
	@enderecoId INT OUTPUT,
	@enderecoTipoLogradouro VARCHAR(20),
    @enderecoLogradouro VARCHAR(255),
	@enderecoNumero VARCHAR(11),
	@enderecoBairro VARCHAR(255),
	@enderecoCEP INT,
	@enderecoTipoId INT,
    @cidadeId INT
AS
BEGIN
	IF NOT EXISTS (SELECT cidade_id FROM cidade WHERE cidade_id = @cidadeId)
	BEGIN
		RAISERROR(50007, 10, 1);
		RETURN
	END

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO [dbo].[endereco]
           ([endereco_tipo_logradouro]
           ,[endereco_logradouro]
           ,[endereco_numero]
           ,[endereco_bairro]
           ,[endereco_cep]
           ,[enderecotipo_id]
           ,[cidade_id])
		 VALUES
           (@enderecoTipoLogradouro
           ,@enderecoLogradouro
           ,@enderecoNumero
           ,@enderecoBairro
           ,@enderecoCEP
           ,@enderecoTipoId
           ,@cidadeId)

		SET @enderecoId = @@IDENTITY;
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spEnderecoTipoInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spEnderecoTipoInserir]
	@descricao VARCHAR(50)
AS
BEGIN
	IF EXISTS (SELECT enderecotipo_id FROM enderecotipo WHERE enderecotipo_descricao = @descricao)
	BEGIN
		RAISERROR(50004, 10, 1);
		RETURN;
	END 

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO enderecotipo
           (enderecotipo_descricao)
		VALUES
           (@descricao)

		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spEstadoInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spEstadoInserir]
	@estadoNome VARCHAR(100),
	@estadoUF CHAR(2)
AS
BEGIN
	IF EXISTS (SELECT estado_id FROM estado WHERE estado_nome = @estadoNome OR estado_uf = @estadoUF)
	BEGIN
		RAISERROR(50005, 10, 1);
		RETURN
	END

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO estado
           (estado_nome
           ,estado_uf)
		VALUES
           (@estadoNome
           ,@estadoUF)
		
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spFuncionarioContatoInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spFuncionarioContatoInserir]
	@funcionarioId INT,
	@contatoTitulo VARCHAR(50),
	@contatoDescricao VARCHAR(50),
	@contatoTipoId INT
AS
BEGIN
	DECLARE @contatoId INT;
	EXEC spContatoInserir @contatoId OUTPUT, @contatoTitulo, @contatoDescricao, @contatoTipoId;

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO funcionariocontato
           (funcionario_id
           ,contato_id)
		VALUES
           (@funcionarioId
           ,@contatoId)
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spFuncionarioEnderecoInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spFuncionarioEnderecoInserir]
	@funcionarioId INT,
	@enderecoTipoLogradouro VARCHAR(20),
    @enderecoLogradouro VARCHAR(255),
	@enderecoNumero VARCHAR(11),
	@enderecoBairro VARCHAR(255),
	@enderecoCEP INT,
	@enderecoTipoId INT,
    @cidadeId INT
AS
BEGIN
	DECLARE @enderecoId INT;
	EXEC spEnderecoInserir @enderecoId OUTPUT, @enderecoTipoLogradouro, @enderecoLogradouro, @enderecoNumero, @enderecoBairro, @enderecoCEP, @enderecoTipoId, @cidadeId;

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO funcionarioendereco
           (funcionario_id
           ,endereco_id)
		VALUES
           (@funcionarioId
           ,@enderecoId)
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spFuncionarioInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spFuncionarioInserir]
	@funcionarioId INT OUTPUT,
	@funcionarioCpf VARCHAR(15),
    @funcionarioRg VARCHAR(15),
    @funcionarioNome VARCHAR(255),
    @funcionarioApelido VARCHAR(50),
    @funcionarioUsuario VARCHAR(20),
    @funcionarioSenha VARCHAR(20),
    @empresaId INT,
    @sttsId INT
AS
BEGIN
	IF NOT EXISTS (SELECT empresa_id FROM empresa WHERE empresa_id = @empresaId)
	BEGIN
		RAISERROR(50010, 10, 1);
		RETURN
	END

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO funcionario
           (funcionario_cpf
           ,funcionario_rg
           ,funcionario_nome
           ,funcionario_apelido
           ,funcionario_usuario
           ,funcionario_senha
           ,empresa_id
           ,stts_id)
		VALUES
           (@funcionarioCpf
           ,@funcionarioRg
           ,@funcionarioNome
           ,@funcionarioApelido
           ,@funcionarioUsuario
           ,@funcionarioSenha
           ,@empresaId
           ,@sttsId);
		   
		SET @funcionarioId = @@IDENTITY;
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spMesaInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spMesaInserir]
	@mesaIdentificacao INT,
    @ambienteId INT
AS
BEGIN
	IF NOT EXISTS (SELECT ambiente_id FROM ambiente WHERE ambiente_id = @ambienteId)
	BEGIN
		RAISERROR(50012, 10, 1);
		RETURN
	END

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO mesa
           (mesa_identificacao
           ,ambiente_id)
     VALUES
           (@mesaIdentificacao
           ,@ambienteId)
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END
GO
/****** Object:  StoredProcedure [dbo].[spPedidoInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spPedidoInserir]
	@pedidoId INT OUTPUT,
	@pedidoData DATETIME,
	@pedidoSubtotal MONEY,
    @pedidoTaxaGarcom MONEY,
    @pedidoValorTotal MONEY,
    @mesaId INT,
    @funcionarioId INT,
    @empresaId INT,
    @sttsId INT
AS
BEGIN
	IF NOT EXISTS (SELECT mesa_id FROM mesa WHERE mesa_id = @mesaId)
	BEGIN
		RAISERROR(50014, 10, 1);
		RETURN
	END

	IF NOT EXISTS (SELECT funcionario_id FROM funcionario WHERE funcionario_id = @funcionarioId)
	BEGIN
		RAISERROR(50013, 10, 1);
		RETURN
	END

	IF NOT EXISTS (SELECT empresa_id FROM empresa WHERE empresa_id = @empresaId)
	BEGIN
		RAISERROR(50010, 10, 1);
		RETURN
	END

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO pedido
           (pedido_data
           ,pedido_subtotal
           ,pedido_taxa_garcom
           ,pedido_valor_total
           ,mesa_id
           ,funcionario_id
           ,empresa_id
           ,stts_id)
		VALUES
           (@pedidoData
           ,@pedidoSubtotal
           ,@pedidoTaxaGarcom
           ,@pedidoValorTotal
           ,@mesaId
           ,@funcionarioId
           ,@empresaId
           ,@sttsId)
		   
		SET @pedidoId = @@IDENTITY;
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spPedidoItemAdicionalInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spPedidoItemAdicionalInserir]
	@produtoAdicionalId INT,
    @pedidoItemId INT
AS
BEGIN
	IF NOT EXISTS (SELECT pedidoitem_id FROM pedidoitem WHERE pedidoitem_id = @pedidoItemId)
	BEGIN
		RAISERROR(50015, 10, 1);
		RETURN
	END

	IF NOT EXISTS (SELECT produtoadicional_id FROM produtoadicional WHERE produtoadicional_id = @produtoAdicionalId)
	BEGIN
		RAISERROR(50016, 10, 1);
		RETURN
	END

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO pedidoitemadicional
           (produtoadicional_id
           ,pedidoitem_id)
		VALUES
           (@produtoAdicionalId
           ,@pedidoItemId)

		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spPedidoItemInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spPedidoItemInserir]
	@pedidoItemId INT OUTPUT,
	@pedidoItemQuantidade INT,
    @pedidoItemObservacao VARCHAR(255),
    @pedidoId INT,
    @produtoId INT
AS
BEGIN
	DECLARE @pedidoItemPrecoVenda MONEY;

	IF NOT EXISTS (SELECT pedido_id FROM pedido WHERE pedido_id = @pedidoId)
	BEGIN
		RAISERROR(50011, 10, 1);
		RETURN
	END

	IF NOT EXISTS (SELECT produto_id FROM produto WHERE produto_id = @produtoId)
	BEGIN
		RAISERROR(50013, 10, 1);
		RETURN
	END
	
	SELECT 
		TOP 1
		produtovenda_preco = @pedidoItemPrecoVenda
	FROM
		produtovenda
	WHERE
		produto_id = @produtoId
		AND stts_id = 1;
	
	BEGIN TRAN
	BEGIN TRY
		INSERT INTO pedidoitem
           (pedidoitem_quantidade
           ,pedidoitem_preco_venda
           ,pedidoitem_valor_total
           ,pedidoitem_observacao
           ,pedido_id
           ,produto_id)
		VALUES
           (@pedidoItemQuantidade
           ,@pedidoItemPrecoVenda
           ,(@pedidoItemQuantidade * @pedidoItemPrecoVenda)
           ,@pedidoItemObservacao
           ,@pedidoId
           ,@produtoId)
		   
		SET @pedidoItemId = @@IDENTITY;
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spProdutoAdicionalInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spProdutoAdicionalInserir]
	@produtoAdicionalId INT OUTPUT,
	@produtoAdicionalCodigo INT,
	@produtoAdicionalDescricao VARCHAR(255),
	@produtoAdicionalPrecoVenda MONEY
AS
BEGIN
	BEGIN TRAN
	BEGIN TRY
		INSERT INTO produtoadicional
           (produtoadicional_codigo
           ,produtoadicional_descricao
           ,produtoadicional_preco_venda)
     VALUES
           (@produtoAdicionalCodigo
           ,@produtoAdicionalDescricao
           ,@produtoAdicionalPrecoVenda)
		   
		SET @produtoAdicionalId = @@IDENTITY;
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spProdutoInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spProdutoInserir]
	@produtoId INT OUTPUT,
	@produtoCodigo INT,
	@produtoNome VARCHAR(150),
	@empresaId INT,
	@sttsId INT
AS
BEGIN
	IF NOT EXISTS (SELECT empresa_id FROM empresa WHERE empresa_id = @empresaId)
	BEGIN
		RAISERROR(50010, 10, 1);
		RETURN
	END

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO produto
           (produto_codigo
           ,produto_nome
           ,empresa_id
           ,stts_id)
		VALUES
           (@produtoCodigo
           ,@produtoNome
           ,@empresaId
           ,@sttsId)

		SET @produtoId = @@IDENTITY;
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spProdutoVendaInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spProdutoVendaInserir]
	@produtoVendaPreco MONEY,
    @produtoId INT,
	@sttsId INT
AS
BEGIN
	IF NOT EXISTS (SELECT produto_id FROM produto WHERE produto_id = @produtoId)
	BEGIN
		RAISERROR(50011, 10, 1);
		RETURN
	END

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO produtovenda
           (produtovenda_preco
           ,produto_id
           ,stts_id)
		VALUES
           (@produtoVendaPreco
           ,@produtoId
           ,@sttsId)

		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spRegistrarMensagemDeErro]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spRegistrarMensagemDeErro]
AS	
BEGIN
	BEGIN TRY
		SET LANGUAGE us_english
		EXEC sp_addmessage 50001, 10, 'Status já cadastrado', @replace = 'REPLACE';
		EXEC sp_addmessage 50002, 10, 'Tipo de contato já cadastrado', @replace = 'REPLACE';
		EXEC sp_addmessage 50003, 10, 'Tipo de contato selecionado não cadastrado', @replace = 'REPLACE';
		EXEC sp_addmessage 50004, 10, 'Tipo de endereço já cadastrado', @replace = 'REPLACE';
		EXEC sp_addmessage 50005, 10, 'Estado já cadastrado', @replace = 'REPLACE';
		EXEC sp_addmessage 50006, 10, 'Estado selecionado não cadastrado', @replace = 'REPLACE';
		EXEC sp_addmessage 50007, 10, 'Cidade selecionada não cadastrada', @replace = 'REPLACE';
		EXEC sp_addmessage 50008, 10, 'Empresa já cadastrada', @replace = 'REPLACE';
		EXEC sp_addmessage 50009, 10, 'Empresa matriz selecionada não cadastrada', @replace = 'REPLACE';
		EXEC sp_addmessage 50010, 10, 'Empresa selecionada não cadastrada', @replace = 'REPLACE';
		EXEC sp_addmessage 50011, 10, 'Produto selecionado não cadastrado', @replace = 'REPLACE';
		EXEC sp_addmessage 50012, 10, 'Ambiente selecionado não cadastrado', @replace = 'REPLACE';
		EXEC sp_addmessage 50013, 10, 'Funcionário selecionado não cadastrado', @replace = 'REPLACE';
		EXEC sp_addmessage 50014, 10, 'Mesa selecionada não cadastrada', @replace = 'REPLACE';
		EXEC sp_addmessage 50015, 10, 'Pedido Item não cadastrado', @replace = 'REPLACE';
		EXEC sp_addmessage 50016, 10, 'Produto adicional não cadastrado', @replace = 'REPLACE';
        EXEC sp_addmessage 50017, 10, 'Tamanho de CNPJ inválido', @replace = 'REPLACE';
        EXEC sp_addmessage 50018, 10, 'CNPJ inválido', @replace = 'REPLACE';
		EXEC sp_addmessage 50019, 10, 'Ambiente já cadastrado para a empresa selecionada', @replace = 'REPLACE';
		EXEC sp_addmessage 50020, 10, 'Ambiente já cadastrado para a empresa selecionada com a ordem solicitada', @replace = 'REPLACE';
	END TRY
	BEGIN CATCH
		RETURN @@ERROR
	END CATCH
END
GO
/****** Object:  StoredProcedure [dbo].[spStatusInserir]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spStatusInserir]
	@descricao VARCHAR(50)
AS
BEGIN
	IF EXISTS (SELECT stts_id FROM stts WHERE stts_descricao = @descricao)
	BEGIN
		RAISERROR(50001, 10, 1);
		RETURN;
	END

	BEGIN TRAN
	BEGIN TRY
		INSERT INTO stts
           (stts_descricao)
		VALUES
           (@descricao)

		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[spValidarCNPJ]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spValidarCNPJ]
    @CNPJ VARCHAR(14),
    @RESULTADO INT = 0 OUTPUT
AS
BEGIN

    DECLARE
        @INDICE INT,
        @SOMA INT,
        @DIG1 INT,
        @DIG2 INT,
        @VAR1 INT,
        @VAR2 INT 

    /* PRIMEIRA PARTE*/
    SET @SOMA = 0
    SET @INDICE = 1
    SET @RESULTADO = 0
    SET @VAR1 = 5   /* algoritimo começando do 5 */
 
    WHILE (@INDICE < = 4)   /* 5432 */
    BEGIN
        SET @SOMA = @SOMA + CONVERT(INT, SUBSTRING(@CNPJ, @INDICE, 1)) * @VAR1
        SET @INDICE = @INDICE + 1
        SET @VAR1 = @VAR1 - 1   /* subtraindo o algorítimo de 5 até 2 */
    END

    SET @VAR2 = 9
    WHILE (@INDICE <= 12)   /* 98765432 */
    BEGIN
        SET @SOMA = @SOMA + CONVERT(INT, SUBSTRING(@CNPJ, @INDICE, 1)) * @VAR2
        SET @INDICE = @INDICE + 1
        SET @VAR2 = @VAR2 - 1   /* subtraindo o algorítimo de 9 até 2 */        
    END
 
    SET @DIG1 = (@SOMA % 11)
 
    /* verifica o resto da divisão para definir o valor do digito 1 */
    IF @DIG1 < 2
        SET @DIG1 = 0;
    ELSE
        SET @DIG1 = 11 - ( @SOMA % 11 );

    /* SEGUNDA PARTE*/
    SET @INDICE = 1
    SET @SOMA = 0
    SET @VAR1 = 6 /* algoritimo começando do 6 */

    WHILE (@INDICE <= 5)    /* 65432 */  
    BEGIN
        SET @SOMA = @SOMA + CONVERT(INT, SUBSTRING(@CNPJ, @INDICE, 1)) * @VAR1
        SET @INDICE = @INDICE + 1
        SET @VAR1 = @VAR1 - 1   /* subtraindo o algorítimo de 6 até 2 */
    END

    SET @VAR2 = 9
    WHILE (@INDICE <= 13)   /* 98765432 */
    BEGIN
        SET @SOMA = @SOMA + CONVERT(INT, SUBSTRING(@CNPJ, @INDICE, 1)) * @VAR2
        SET @INDICE = @INDICE + 1
        SET @VAR2 = @VAR2 - 1   /* subtraindo o algorítimo de 9 até 2 */
    END
    
    SET @DIG2 = ( @SOMA % 11 )

    /* verifica o resto da divisão para definir o valor do digito 2 */ 
    IF @DIG2 < 2
        SET @DIG2 = 0; 
    ELSE
        SET @DIG2 = 11 - ( @SOMA % 11 ); 

    IF ( @DIG1 = SUBSTRING(@CNPJ, LEN(@CNPJ) - 1, 1) ) AND ( @DIG2 = SUBSTRING(@CNPJ, LEN(@CNPJ), 1) )
        SET @RESULTADO = 1
    ELSE
        SET @RESULTADO = 0
 
END

GO
/****** Object:  Table [dbo].[ambiente]    Script Date: 17/10/2018 22:09:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ambiente](
	[ambiente_id] [int] IDENTITY(1,1) NOT NULL,
	[ambiente_nome] [varchar](45) NULL,
	[ambiente_ordem] [int] NULL,
	[empresa_id] [int] NULL,
 CONSTRAINT [PK_ambiente] PRIMARY KEY CLUSTERED 
(
	[ambiente_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[cidade]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[cidade](
	[cidade_id] [int] IDENTITY(1,1) NOT NULL,
	[cidade_nome] [varchar](255) NULL,
	[estado_id] [int] NULL,
 CONSTRAINT [PK_cidade] PRIMARY KEY CLUSTERED 
(
	[cidade_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[contato]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[contato](
	[contato_id] [int] IDENTITY(1,1) NOT NULL,
	[contato_titulo] [varchar](50) NULL,
	[contato_descricao] [varchar](50) NULL,
	[contatotipo_id] [int] NULL,
 CONSTRAINT [PK_contato] PRIMARY KEY CLUSTERED 
(
	[contato_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[contatotipo]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[contatotipo](
	[contatotipo_id] [int] IDENTITY(1,1) NOT NULL,
	[contatotipo_descricao] [varchar](100) NULL,
 CONSTRAINT [PK_contatotipo] PRIMARY KEY CLUSTERED 
(
	[contatotipo_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[empresa]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[empresa](
	[empresa_id] [int] IDENTITY(1,1) NOT NULL,
	[empresa_razao_social] [varchar](255) NULL,
	[empresa_nome_fantasia] [varchar](255) NULL,
	[empresa_cnpj] [varchar](15) NULL,
	[empresa_ie] [varchar](15) NULL,
	[empresa_matriz] [int] NULL,
 CONSTRAINT [PK_empresa] PRIMARY KEY CLUSTERED 
(
	[empresa_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[empresacontato]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[empresacontato](
	[empresacontato_id] [int] IDENTITY(1,1) NOT NULL,
	[empresa_id] [int] NULL,
	[contato_id] [int] NULL,
 CONSTRAINT [PK_empresacontato] PRIMARY KEY CLUSTERED 
(
	[empresacontato_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[empresaendereco]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[empresaendereco](
	[empresaendereco_id] [int] IDENTITY(1,1) NOT NULL,
	[endereco_id] [int] NULL,
	[empresa_id] [int] NULL,
 CONSTRAINT [PK_empresaendereco] PRIMARY KEY CLUSTERED 
(
	[empresaendereco_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[endereco]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[endereco](
	[endereco_id] [int] IDENTITY(1,1) NOT NULL,
	[endereco_tipo_logradouro] [varchar](20) NULL,
	[endereco_logradouro] [varchar](255) NULL,
	[endereco_numero] [varchar](11) NULL,
	[endereco_bairro] [varchar](255) NULL,
	[endereco_cep] [int] NULL,
	[enderecotipo_id] [int] NULL,
	[cidade_id] [int] NULL,
 CONSTRAINT [PK_endereco] PRIMARY KEY CLUSTERED 
(
	[endereco_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[enderecotipo]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[enderecotipo](
	[enderecotipo_id] [int] IDENTITY(1,1) NOT NULL,
	[enderecotipo_descricao] [varchar](100) NULL,
 CONSTRAINT [PK_enderecotipo] PRIMARY KEY CLUSTERED 
(
	[enderecotipo_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[estado]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[estado](
	[estado_id] [int] IDENTITY(1,1) NOT NULL,
	[estado_nome] [varchar](100) NULL,
	[estado_uf] [char](2) NULL,
 CONSTRAINT [PK_estado] PRIMARY KEY CLUSTERED 
(
	[estado_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[funcionario]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[funcionario](
	[funcionario_id] [int] IDENTITY(1,1) NOT NULL,
	[funcionario_cpf] [varchar](15) NULL,
	[funcionario_rg] [varchar](15) NULL,
	[funcionario_nome] [varchar](255) NULL,
	[funcionario_apelido] [varchar](50) NULL,
	[funcionario_usuario] [varchar](20) NULL,
	[funcionario_senha] [varchar](20) NULL,
	[empresa_id] [int] NULL,
	[stts_id] [int] NULL,
 CONSTRAINT [PK_funcionario] PRIMARY KEY CLUSTERED 
(
	[funcionario_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[funcionariocontato]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[funcionariocontato](
	[funcionariocontato_id] [int] IDENTITY(1,1) NOT NULL,
	[funcionario_id] [int] NULL,
	[contato_id] [int] NULL,
 CONSTRAINT [PK_funcionariocontato] PRIMARY KEY CLUSTERED 
(
	[funcionariocontato_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[funcionarioendereco]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[funcionarioendereco](
	[funcionarioendereco_id] [int] IDENTITY(1,1) NOT NULL,
	[endereco_id] [int] NULL,
	[funcionario_id] [int] NULL,
 CONSTRAINT [PK_funcionarioendereco] PRIMARY KEY CLUSTERED 
(
	[funcionarioendereco_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[mesa]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[mesa](
	[mesa_id] [int] IDENTITY(1,1) NOT NULL,
	[mesa_identificacao] [int] NULL,
	[ambiente_id] [int] NULL,
 CONSTRAINT [PK_mesa] PRIMARY KEY CLUSTERED 
(
	[mesa_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[pedido]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[pedido](
	[pedido_id] [int] IDENTITY(1,1) NOT NULL,
	[pedido_data] [datetime] NULL,
	[pedido_subtotal] [money] NULL,
	[pedido_taxa_garcom] [money] NULL,
	[pedido_valor_total] [money] NULL,
	[mesa_id] [int] NULL,
	[funcionario_id] [int] NULL,
	[empresa_id] [int] NULL,
	[stts_id] [int] NULL,
 CONSTRAINT [PK_pedido] PRIMARY KEY CLUSTERED 
(
	[pedido_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[pedidoitem]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[pedidoitem](
	[pedidoitem_id] [int] IDENTITY(1,1) NOT NULL,
	[pedidoitem_quantidade] [int] NULL,
	[pedidoitem_preco_venda] [money] NULL,
	[pedidoitem_valor_total] [money] NULL,
	[pedidoitem_observacao] [varchar](255) NULL,
	[pedido_id] [int] NULL,
	[produto_id] [int] NULL,
 CONSTRAINT [PK_pedidoitem] PRIMARY KEY CLUSTERED 
(
	[pedidoitem_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[pedidoitemadicional]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[pedidoitemadicional](
	[pedidoitemadicional_id] [int] IDENTITY(1,1) NOT NULL,
	[produtoadicional_id] [int] NULL,
	[pedidoitem_id] [int] NULL,
 CONSTRAINT [PK_pedidoitemadicional] PRIMARY KEY CLUSTERED 
(
	[pedidoitemadicional_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[produto]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[produto](
	[produto_id] [int] IDENTITY(1,1) NOT NULL,
	[produto_codigo] [int] NULL,
	[produto_nome] [varchar](150) NULL,
	[empresa_id] [int] NULL,
	[stts_id] [int] NULL,
 CONSTRAINT [PK_produto] PRIMARY KEY CLUSTERED 
(
	[produto_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[produtoadicional]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[produtoadicional](
	[produtoadicional_id] [int] IDENTITY(1,1) NOT NULL,
	[produtoadicional_codigo] [int] NULL,
	[produtoadicional_descricao] [varchar](255) NULL,
	[produtoadicional_preco_venda] [money] NULL,
 CONSTRAINT [PK_produtoadicional] PRIMARY KEY CLUSTERED 
(
	[produtoadicional_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[produtovenda]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[produtovenda](
	[produtovenda_id] [int] IDENTITY(1,1) NOT NULL,
	[produtovenda_preco] [money] NULL,
	[produto_id] [int] NULL,
	[stts_id] [int] NULL,
 CONSTRAINT [PK_produtovenda] PRIMARY KEY CLUSTERED 
(
	[produtovenda_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[stts]    Script Date: 17/10/2018 22:09:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[stts](
	[stts_id] [int] IDENTITY(1,1) NOT NULL,
	[stts_descricao] [varchar](50) NULL,
 CONSTRAINT [PK_stts] PRIMARY KEY CLUSTERED 
(
	[stts_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[ambiente]  WITH CHECK ADD  CONSTRAINT [FK_ambiente_empresa] FOREIGN KEY([empresa_id])
REFERENCES [dbo].[empresa] ([empresa_id])
GO
ALTER TABLE [dbo].[ambiente] CHECK CONSTRAINT [FK_ambiente_empresa]
GO
ALTER TABLE [dbo].[cidade]  WITH CHECK ADD  CONSTRAINT [FK_cidade_estado] FOREIGN KEY([estado_id])
REFERENCES [dbo].[estado] ([estado_id])
GO
ALTER TABLE [dbo].[cidade] CHECK CONSTRAINT [FK_cidade_estado]
GO
ALTER TABLE [dbo].[contato]  WITH CHECK ADD  CONSTRAINT [FK_contato_contatotipo] FOREIGN KEY([contatotipo_id])
REFERENCES [dbo].[contatotipo] ([contatotipo_id])
GO
ALTER TABLE [dbo].[contato] CHECK CONSTRAINT [FK_contato_contatotipo]
GO
ALTER TABLE [dbo].[empresa]  WITH CHECK ADD  CONSTRAINT [FK_empresa_empresa] FOREIGN KEY([empresa_matriz])
REFERENCES [dbo].[empresa] ([empresa_id])
GO
ALTER TABLE [dbo].[empresa] CHECK CONSTRAINT [FK_empresa_empresa]
GO
ALTER TABLE [dbo].[empresacontato]  WITH CHECK ADD  CONSTRAINT [FK_empresacontato_contato] FOREIGN KEY([contato_id])
REFERENCES [dbo].[contato] ([contato_id])
GO
ALTER TABLE [dbo].[empresacontato] CHECK CONSTRAINT [FK_empresacontato_contato]
GO
ALTER TABLE [dbo].[empresacontato]  WITH CHECK ADD  CONSTRAINT [FK_empresacontato_empresa] FOREIGN KEY([empresa_id])
REFERENCES [dbo].[empresa] ([empresa_id])
GO
ALTER TABLE [dbo].[empresacontato] CHECK CONSTRAINT [FK_empresacontato_empresa]
GO
ALTER TABLE [dbo].[empresaendereco]  WITH CHECK ADD  CONSTRAINT [FK_empresaendereco_empresa] FOREIGN KEY([empresa_id])
REFERENCES [dbo].[empresa] ([empresa_id])
GO
ALTER TABLE [dbo].[empresaendereco] CHECK CONSTRAINT [FK_empresaendereco_empresa]
GO
ALTER TABLE [dbo].[empresaendereco]  WITH CHECK ADD  CONSTRAINT [FK_empresaendereco_endereco] FOREIGN KEY([endereco_id])
REFERENCES [dbo].[endereco] ([endereco_id])
GO
ALTER TABLE [dbo].[empresaendereco] CHECK CONSTRAINT [FK_empresaendereco_endereco]
GO
ALTER TABLE [dbo].[endereco]  WITH CHECK ADD  CONSTRAINT [FK_endereco_cidade] FOREIGN KEY([cidade_id])
REFERENCES [dbo].[cidade] ([cidade_id])
GO
ALTER TABLE [dbo].[endereco] CHECK CONSTRAINT [FK_endereco_cidade]
GO
ALTER TABLE [dbo].[endereco]  WITH CHECK ADD  CONSTRAINT [FK_endereco_enderecotipo] FOREIGN KEY([enderecotipo_id])
REFERENCES [dbo].[enderecotipo] ([enderecotipo_id])
GO
ALTER TABLE [dbo].[endereco] CHECK CONSTRAINT [FK_endereco_enderecotipo]
GO
ALTER TABLE [dbo].[funcionario]  WITH CHECK ADD  CONSTRAINT [FK_funcionario_empresa] FOREIGN KEY([empresa_id])
REFERENCES [dbo].[empresa] ([empresa_id])
GO
ALTER TABLE [dbo].[funcionario] CHECK CONSTRAINT [FK_funcionario_empresa]
GO
ALTER TABLE [dbo].[funcionario]  WITH CHECK ADD  CONSTRAINT [FK_funcionario_stts] FOREIGN KEY([stts_id])
REFERENCES [dbo].[stts] ([stts_id])
GO
ALTER TABLE [dbo].[funcionario] CHECK CONSTRAINT [FK_funcionario_stts]
GO
ALTER TABLE [dbo].[funcionariocontato]  WITH CHECK ADD  CONSTRAINT [FK_funcionariocontato_contato] FOREIGN KEY([contato_id])
REFERENCES [dbo].[contato] ([contato_id])
GO
ALTER TABLE [dbo].[funcionariocontato] CHECK CONSTRAINT [FK_funcionariocontato_contato]
GO
ALTER TABLE [dbo].[funcionariocontato]  WITH CHECK ADD  CONSTRAINT [FK_funcionariocontato_funcionario] FOREIGN KEY([funcionario_id])
REFERENCES [dbo].[funcionario] ([funcionario_id])
GO
ALTER TABLE [dbo].[funcionariocontato] CHECK CONSTRAINT [FK_funcionariocontato_funcionario]
GO
ALTER TABLE [dbo].[funcionarioendereco]  WITH CHECK ADD  CONSTRAINT [FK_funcionarioendereco_endereco] FOREIGN KEY([endereco_id])
REFERENCES [dbo].[endereco] ([endereco_id])
GO
ALTER TABLE [dbo].[funcionarioendereco] CHECK CONSTRAINT [FK_funcionarioendereco_endereco]
GO
ALTER TABLE [dbo].[funcionarioendereco]  WITH CHECK ADD  CONSTRAINT [FK_funcionarioendereco_funcionario] FOREIGN KEY([funcionario_id])
REFERENCES [dbo].[funcionario] ([funcionario_id])
GO
ALTER TABLE [dbo].[funcionarioendereco] CHECK CONSTRAINT [FK_funcionarioendereco_funcionario]
GO
ALTER TABLE [dbo].[mesa]  WITH CHECK ADD  CONSTRAINT [FK_mesa_ambiente] FOREIGN KEY([ambiente_id])
REFERENCES [dbo].[ambiente] ([ambiente_id])
GO
ALTER TABLE [dbo].[mesa] CHECK CONSTRAINT [FK_mesa_ambiente]
GO
ALTER TABLE [dbo].[pedido]  WITH CHECK ADD  CONSTRAINT [FK_pedido_empresa] FOREIGN KEY([empresa_id])
REFERENCES [dbo].[empresa] ([empresa_id])
GO
ALTER TABLE [dbo].[pedido] CHECK CONSTRAINT [FK_pedido_empresa]
GO
ALTER TABLE [dbo].[pedido]  WITH CHECK ADD  CONSTRAINT [FK_pedido_funcionario] FOREIGN KEY([funcionario_id])
REFERENCES [dbo].[funcionario] ([funcionario_id])
GO
ALTER TABLE [dbo].[pedido] CHECK CONSTRAINT [FK_pedido_funcionario]
GO
ALTER TABLE [dbo].[pedido]  WITH CHECK ADD  CONSTRAINT [FK_pedido_mesa] FOREIGN KEY([mesa_id])
REFERENCES [dbo].[mesa] ([mesa_id])
GO
ALTER TABLE [dbo].[pedido] CHECK CONSTRAINT [FK_pedido_mesa]
GO
ALTER TABLE [dbo].[pedidoitem]  WITH CHECK ADD  CONSTRAINT [FK_pedidoitem_pedido] FOREIGN KEY([pedido_id])
REFERENCES [dbo].[pedido] ([pedido_id])
GO
ALTER TABLE [dbo].[pedidoitem] CHECK CONSTRAINT [FK_pedidoitem_pedido]
GO
ALTER TABLE [dbo].[pedidoitem]  WITH CHECK ADD  CONSTRAINT [FK_pedidoitem_produto] FOREIGN KEY([produto_id])
REFERENCES [dbo].[produto] ([produto_id])
GO
ALTER TABLE [dbo].[pedidoitem] CHECK CONSTRAINT [FK_pedidoitem_produto]
GO
ALTER TABLE [dbo].[pedidoitemadicional]  WITH CHECK ADD  CONSTRAINT [FK_pedidoitemadicional_pedidoitem] FOREIGN KEY([pedidoitem_id])
REFERENCES [dbo].[pedidoitem] ([pedidoitem_id])
GO
ALTER TABLE [dbo].[pedidoitemadicional] CHECK CONSTRAINT [FK_pedidoitemadicional_pedidoitem]
GO
ALTER TABLE [dbo].[pedidoitemadicional]  WITH CHECK ADD  CONSTRAINT [FK_pedidoitemadicional_produtoadicional] FOREIGN KEY([produtoadicional_id])
REFERENCES [dbo].[produtoadicional] ([produtoadicional_id])
GO
ALTER TABLE [dbo].[pedidoitemadicional] CHECK CONSTRAINT [FK_pedidoitemadicional_produtoadicional]
GO
ALTER TABLE [dbo].[produto]  WITH CHECK ADD  CONSTRAINT [FK_produto_empresa] FOREIGN KEY([empresa_id])
REFERENCES [dbo].[empresa] ([empresa_id])
GO
ALTER TABLE [dbo].[produto] CHECK CONSTRAINT [FK_produto_empresa]
GO
ALTER TABLE [dbo].[produto]  WITH CHECK ADD  CONSTRAINT [FK_produto_stts] FOREIGN KEY([stts_id])
REFERENCES [dbo].[stts] ([stts_id])
GO
ALTER TABLE [dbo].[produto] CHECK CONSTRAINT [FK_produto_stts]
GO
ALTER TABLE [dbo].[produtovenda]  WITH CHECK ADD  CONSTRAINT [FK_produtovenda_produto] FOREIGN KEY([produto_id])
REFERENCES [dbo].[produto] ([produto_id])
GO
ALTER TABLE [dbo].[produtovenda] CHECK CONSTRAINT [FK_produtovenda_produto]
GO
ALTER TABLE [dbo].[produtovenda]  WITH CHECK ADD  CONSTRAINT [FK_produtovenda_stts] FOREIGN KEY([stts_id])
REFERENCES [dbo].[stts] ([stts_id])
GO
ALTER TABLE [dbo].[produtovenda] CHECK CONSTRAINT [FK_produtovenda_stts]
GO
USE [master]
GO
ALTER DATABASE [icomida] SET  READ_WRITE 
GO

USE [icomida]
CREATE VIEW [dbo].[PedidoPendenteCozinha]
AS
	SELECT TOP 10
		CONVERT(VARCHAR, ped.pedido_id)  AS pedido,
		CONVERT(VARCHAR, pedit.pedidoitem_quantidade) AS qtd,
		CONVERT(VARCHAR, prod.produto_nome) AS produto,
		CONVERT(VARCHAR, pedit.pedidoitem_observacao) AS obs
	FROM
		pedido ped, pedidoitem pedit, produto prod
	WHERE
		ped.pedido_id = pedit.pedido_id
		AND pedit.produto_id = prod.produto_id
		AND pedit.stts_id = 3
	ORDER BY ped.pedido_data DESC
GO