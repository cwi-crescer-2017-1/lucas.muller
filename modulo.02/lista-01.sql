/*
    --- LISTA DE EXERCÍCIOS 1 ---
	 
	    https://goo.gl/WWuymZ
*/

-- Transações:
-- begin transaction - inicia a transação
-- rollback          - cancela a transação
-- commit            - efetiva a transação

-- exercício 1:
-- select * into CidadeAux from Cidade

-- exercício 2:
-- truncate table CidadeAux   ou   delete from CidadeAux
-- insert into CidadeAux select * from Cidade where UF='RS'

-- exercício 3:
/*
create table Produtos (
	IDProduto       int identity not null,
	Nome            varchar(50) not null,
	NomeDescritivo  varchar(250) not null,
	DataCriacao     datetime not null,
	LocalEstoque    varchar(100) not null,
	Quantidade      int not null,
	Preco           real not null,
		constraint PK_IDProduto primary key(IDProduto)
)
*/

-- exercício 4:
/*
insert into Produtos (Nome, NomeDescritivo, DataCriacao, LocalEstoque, Quantidade, Preco) values
	('TV Samsung 42"', 'SmartTV 42 polegadas da Samsung com conexão a Internet', GETDATE(), 'Corredor 5B', 10, 1499.99),
	('Computador Gamer', 'Computador Gamer com Intel i7-7700k, 16GB RAM, HD de 1TB e GTX 1080TI', GETDATE(), 'Corredor 7C', 5, 7999.99)
*/