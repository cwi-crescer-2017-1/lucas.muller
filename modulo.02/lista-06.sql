/*
   --- LISTA DE EXERCÍCIOS 06 ---

       https://goo.gl/cZm1nF
*/

/* exercício 01:
   Lista qual o primeiro nome mais popular entre os clientes, considere apenas o primeiro nome.
*/
select TOP 1 SUBSTRING(Nome, 1, CHARINDEX(' ', Nome) - 1) AS PrimeiroNome,
       COUNT(1) AS NumOcorrencias 
from Cliente
group by SUBSTRING(Nome, 1, CHARINDEX(' ', Nome) - 1)
order by NumOcorrencias desc, PrimeiroNome;

/* exercício 02:
   Liste o total de pedidos (quantidade e valor) realizados no mês de abril/2017.
*/
select COUNT(1) as QuantPedidos, 
       SUM(ped.ValorPedido) as ValorTotal
from Pedido ped
where DATEPART(year, ped.DataPedido) = 2017 and DATEPART(month, ped.DataPedido) = 4;

/* exercício 03:
   Identifique qual o estado (coluna UF da tabela Cidade) possuí o maior número de clientes (tabela Cliente), 
   liste também qual o Estado possuí o menor número de clientes.
*/
(select * from 
	(select TOP 1 cid.UF, COUNT(1) as QuantClientes
	from Cidade cid
	join Cliente cli on cli.IDCidade = cid.IDCidade
	group by cid.UF
	order by QuantClientes desc) as MaisClientes
union
select * from 
	(select TOP 1 cid.UF, COUNT(1) as QuantClientes
	from Cidade cid
	join Cliente cli on cli.IDCidade = cid.IDCidade
	group by cid.UF
	order by QuantClientes asc) as MenosClientes);

/* exercício 04:
   Crie (insira) um novo registro na tabela de Produto, com as seguintes informações:
		Nome: Galocha Maragato
		Preço de custo: 35.67
		Preço de venda: 77.95
		Situação: A
*/
insert into Produto (Nome, PrecoCusto, PrecoVenda, Situacao) values
	('Galocha Maragato', 35.67, 77.95, 'A');

/* exercício 05:
   Identifique e liste os produtos que não tiveram nenhum pedido, considere os relacionamentos 
   no modelo de dados, pois não há relacionamento direto 
   entre Produto e Pedido (será preciso relacionar PedidoItem).

   => Obs.: o produto criado anteriormente deverá ser listado (apenas este)
*/
(select * from Produto
where IDProduto NOT IN 
	(select prod.IDProduto from Produto prod
	join PedidoItem item on item.IDProduto = prod.IDProduto
	group by prod.IDProduto));

/* exercício 06:
   Liste os 30 produtos que mais geraram lucro em 2016.
*/
(select * from Produto
where IDProduto IN 
	(select TOP 30 prod.IDProduto
	from PedidoItem item
	join Produto prod on item.IDProduto = prod.IDProduto
	join Pedido ped on ped.IDPedido = item.IDPedido
	where DATEPART(year, ped.DataPedido) = 2016
	group by prod.IDProduto
	order by SUM((prod.PrecoVenda - prod.PrecoCusto) * item.Quantidade) desc));