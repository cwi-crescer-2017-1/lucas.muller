/*
    --- LISTA DE EXERCÍCIOS 2 ---
	 
	    https://goo.gl/pzpfQd
*/

-- Transações:
-- begin transaction - inicia a transação
-- rollback          - cancela a transação
-- commit            - efetiva a transação

-- exercício 1:
-- select IDEmpregado, NomeEmpregado from Empregado order by DataAdmissao;

-- exercício 2:
-- select * from Empregado where Comissao is null order by Salario;

-- exercício 3:
/*
select IDEmpregado,
       NomeEmpregado, 
       (Salario*13)  as SalarioAnual,
	   (ISNULL(Comissao,0)*12) as ComissaoAnual,
	   ((Salario*13) + (ISNULL(Comissao,0)*12)) as RendaAnual
	   from Empregado
*/

-- exercício 4:
/*
select IDEmpregado, NomeEmpregado, Cargo, Salario 
	   from Empregado
	   where (Salario*13) < 18500 or Cargo = 'Atendente'
*/