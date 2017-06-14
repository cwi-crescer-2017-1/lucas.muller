/* --- LISTA DE EXERCICIO 02 --- */
/*     https://goo.gl/YlKcfI     */

-- 01 - Atualmente a tabela de Cidade tem registros duplicados (nome e UF). 
--      Faça um código (PL/SQL) que liste quais são as cidades duplicadas. 
--      Após isso liste todos os clientes que estão relacionados com estas cidades.
DECLARE
  CURSOR CidadesDuplicadas IS
    SELECT c1.Nome, c1.UF, COUNT(*) AS Duplicacoes
    FROM Cidade c1
      JOIN Cidade c2 ON (c2.IDCidade <> c1.IDCidade
        AND c1.Nome = c2.Nome AND c1.UF = c2.UF)
    GROUP BY c1.Nome, c1.UF    
    ORDER BY c1.Nome;
  CURSOR ClientesDaCidade (vNomeCidade IN varchar2, vUFCidade IN varchar2) IS
    SELECT cli.* FROM Cidade cid
      JOIN Cliente cli ON cli.IDCIDADE = cid.IDCIDADE
    WHERE cid.Nome = vNomeCidade AND cid.UF = vUFCidade;
BEGIN
  DBMS_OUTPUT.PUT_LINE('Cidades duplicadas: ');
  DBMS_OUTPUT.PUT_LINE('');
  FOR Cidade IN CidadesDuplicadas LOOP
    DBMS_OUTPUT.PUT_LINE(Cidade.Nome || ' (' || Cidade.UF || ') - duplicada ' || Cidade.Duplicacoes || ' vezes');
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('  Clientes desta cidade:');
    FOR Cliente IN ClientesDaCidade(Cidade.Nome, Cidade.UF) LOOP
      DBMS_OUTPUT.PUT_LINE('  - (' || Cliente.IDCliente || ') ' || Cliente.Nome);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('');
  END LOOP;
END;

-- 02 - Faça uma rotina que permita atualizar o valor do pedido a partir dos seus itens. 
--      Esta rotina deve receber por parametro o IDPedido e então verificar o valor total de seus itens (quantidade x valor unitário).
DECLARE
  vIDPedido number := :IDPedido;
  vValorFinal number;
BEGIN
  SELECT SUM(Quantidade * PrecoUnitario) AS ValorFinal
    INTO vValorFinal
  FROM PedidoItem
  WHERE IDPedido = vIDPedido;
  UPDATE Pedido SET VALORPEDIDO = vValorFinal WHERE IDPedido = vIDPedido;
  COMMIT;
  DBMS_OUTPUT.PUT_LINE('Pedido atualizado! Valor final: ' || TO_CHAR(vValorFinal, 'L99G999D99MI'));
END;

---------------------------------------
SELECT * FROM 
  (SELECT c1.*, 
    ROW_NUMBER() OVER (PARTITION BY c1.Nome, c2.UF ORDER BY c1.IDCidade ASC) Rank
  FROM Cidade c1
    JOIN Cidade c2 ON (c2.IDCidade <> c1.IDCidade
      AND c1.Nome = c2.Nome AND c1.UF = c2.UF))
WHERE Rank = 1      
ORDER BY Nome;

/* 03 - Crie uma rotina que atualize todos os clientes que não realizaram nenhum pedido 
        nos últimos 6 meses (considere apenas o mês, dia 01 do 6º mês anterior). 
        Definir o atributo Situacao para I. */
BEGIN
  UPDATE Cliente SET Situacao = 'I'
  WHERE 
    IDCliente NOT IN (
      SELECT DISTINCT cli.IDCLIENTE FROM Pedido ped
      JOIN Cliente cli on cli.IDCLIENTE = ped.IDCLIENTE
      WHERE ped.DATAPEDIDO >= ADD_MONTHS(TRUNC(SYSDATE, 'mm'), -6))
    AND Situacao != 'I';
  COMMIT;
  DBMS_OUTPUT.PUT_LINE('Clientes inativos atualizados!');
END;

/* 04 - Faça uma rotina que receba IDProduto e Mês e Ano.
        E então faça uma rotina que verifique no ANO/MÊS para o produto informado 
        qual a lista de materiais (incluindo a quantidade) é necessário para atender todos os Pedidos desde período. 
        Deve imprimir o nome do material e quantidade total.*/
DECLARE
  vIDProduto number := :IDProduto;
  vMes number := :Mes;
  vAno number := :Ano;
  CURSOR Materiais IS
    SELECT mat.IDMaterial, mat.Descricao, SUM(prodMat.Quantidade * item.Quantidade) AS Quantidade
    FROM Pedido ped
    JOIN PedidoItem item ON item.IDPedido = ped.IDPedido
    JOIN Produto prod ON prod.IDProduto = item.IDProduto
    JOIN ProdutoMaterial prodMat ON prodMat.IDProduto = prod.IDProduto
    JOIN Material mat ON mat.IDMaterial = prodMat.IDMaterial
    WHERE prod.IDProduto = vIDProduto 
      AND EXTRACT(month FROM ped.DataPedido) = vMes 
      AND EXTRACT(year FROM ped.DataPedido) = vAno 
    GROUP BY mat.IDMaterial, mat.Descricao;
BEGIN
  DBMS_OUTPUT.PUT_LINE('Quantidade de materiais necessários para o período:');
  FOR Material IN Materiais LOOP
    DBMS_OUTPUT.PUT_LINE(Material.Descricao || ' - ' || Material.Quantidade);
  END LOOP;
END;