/* -- LISTA DE EXERCICIO 03 --
      https://goo.gl/7BJSEB    */

-- 01 - REMOVER CIDADES DUPLICADAS
CREATE OR REPLACE PACKAGE PCK_CRESCER AS
  PROCEDURE Remover_Cidades_Duplicadas;
END PCK_CRESCER;

CREATE OR REPLACE PACKAGE BODY PCK_CRESCER AS
  PROCEDURE Remover_Cidades_Duplicadas IS
    CURSOR CidadesDuplicadas IS 
      SELECT MIN(c1.IDCidade) AS MenorIDCidade, 
             c1.Nome, c1.UF, 
             COUNT(*) AS Duplicacoes
      FROM Cidade c1
      GROUP BY c1.Nome, c1.UF 
      HAVING COUNT(*) > 1
      ORDER BY c1.Nome;
      
    CURSOR ClientesDaCidade(vNomeCidade IN varchar2, vUFCidade IN varchar2) IS
      SELECT cli.* FROM Cidade cid
        JOIN Cliente cli ON cli.IDCIDADE = cid.IDCIDADE
      WHERE cid.Nome = vNomeCidade AND cid.UF = vUFCidade;
  BEGIN
    FOR fCidade IN CidadesDuplicadas LOOP
        FOR fCliente IN ClientesDaCidade(fCidade.Nome, fCidade.UF) LOOP
          UPDATE Cliente SET IDCidade = fCidade.MenorIDCidade WHERE IDCliente = fCliente.IDCliente;
        END LOOP;
        DELETE Cidade WHERE Nome = fCidade.Nome AND UF = fCidade.UF AND IDCidade != fCidade.MenorIDCidade;
        DBMS_OUTPUT.PUT_LINE('Removidas duplicatas da cidade ' || fCidade.Nome || ' (' || fCidade.UF || ') com sucesso!');
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('Procedure executada com sucesso!');
  END Remover_Cidades_Duplicadas;
END PCK_CRESCER;

BEGIN
  PCK_CRESCER.Remover_Cidades_Duplicadas;
END;