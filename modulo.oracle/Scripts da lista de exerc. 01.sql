/* ---- LISTA DE EXERCÍCIO 01 ---- */
/*      https://goo.gl/UAkKRw      */

/* Atualizar estatísticas manualmente p/ melhorar desempenho: */
EXEC DBMS_STATS.GATHER_SCHEMA_STATS(USER); 
SELECT TABLE_NAME, TABLESPACE_NAME, NUM_ROWS, BLOCKS, LAST_ANALYZED FROM USER_TABLES;

/* 01 - Liste os produtos (id e nome) que não tiveram nenhuma compra nos últimos quatro meses. */
CREATE OR REPLACE VIEW vwProdutos_Sem_Compras AS
SELECT IDPRODUTO, NOME FROM PRODUTO
WHERE IDPRODUTO NOT IN (
  /* Poderia ter sido criada uma view para este select:
  
  CREATE OR REPLACE VIEW vwProdutosRecentes AS */
  SELECT ITEM.IDPRODUTO
  FROM PEDIDOITEM ITEM
  JOIN PEDIDO PED ON PED.IDPEDIDO = ITEM.IDPEDIDO
  WHERE PED.DATAPEDIDO >= ADD_MONTHS(TRUNC(SYSDATE),-4)
  GROUP BY ITEM.IDPRODUTO
)
ORDER BY IDPRODUTO;
SELECT * FROM VWPRODUTOS_SEM_COMPRAS;

/* 02 - Altere o status dos produtos (campo situacao) que não tiveram nenhum pedido nos últimos quatro meses. */
UPDATE PRODUTO
SET SITUACAO = 'I'
WHERE IDPRODUTO IN (SELECT IDPRODUTO FROM VWPRODUTOS_SEM_COMPRAS);

/* 03 - Faça uma consulta que receba um parâmetro sendo o IDProduto e liste a quantidade de itens 
        na tabela PedidoItem com este IDProduto foram vendidos no último ano (desde janeiro/2017). */
SELECT SUM(ITEM.QUANTIDADE) AS QUANTIDADE
FROM PEDIDOITEM ITEM
JOIN PEDIDO PED ON PED.IDPEDIDO = ITEM.IDPEDIDO
WHERE PED.DATAPEDIDO >= TRUNC(SYSDATE, 'YYYY')
  AND ITEM.IDPRODUTO = :IDPRODUTO;