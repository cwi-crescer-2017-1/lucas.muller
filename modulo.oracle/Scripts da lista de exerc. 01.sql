/* -- LISTA DE EXERCÍCIO 01 -- */

/* 01 - Liste os produtos (id e nome) que não tiveram nenhuma compra nos últimos quatro meses. */
SELECT IDPRODUTO, NOME FROM PRODUTO
WHERE IDPRODUTO NOT IN (
  SELECT ITEM.IDPRODUTO
  FROM PEDIDOITEM ITEM
  JOIN PEDIDO PED ON PED.IDPEDIDO = ITEM.IDPEDIDO
  WHERE PED.DATAPEDIDO >= ADD_MONTHS(TRUNC(SYSDATE),-4)
  GROUP BY ITEM.IDPRODUTO
)
ORDER BY IDPRODUTO;

/* 02 - Altere o status dos produtos (campo situacao) que não tiveram nenhum pedido nos últimos quatro meses. */
UPDATE PRODUTO
SET SITUACAO = 'L'
WHERE IDPRODUTO NOT IN (
  SELECT ITEM.IDPRODUTO
  FROM PEDIDOITEM ITEM
  JOIN PEDIDO PED ON PED.IDPEDIDO = ITEM.IDPEDIDO
  WHERE PED.DATAPEDIDO >= ADD_MONTHS(TRUNC(SYSDATE),-4)
  GROUP BY ITEM.IDPRODUTO
);
COMMIT; ROLLBACK;

/* 03 - Faça uma consulta que receba um parâmetro sendo o IDProduto e liste a quantidade de itens 
        na tabela PedidoItem com este IDProduto foram vendidos no último ano (desde janeiro/2017). */
SELECT ITEM.IDPRODUTO, COUNT(*) AS QUANTIDADE
FROM PEDIDOITEM ITEM
JOIN PEDIDO PED ON PED.IDPEDIDO = ITEM.IDPEDIDO
WHERE PED.DATAPEDIDO >= TO_DATE('2017-01-01', 'yyyy-mm-dd')
  AND ITEM.IDPRODUTO = :IDPRODUTO
GROUP BY ITEM.IDPRODUTO;