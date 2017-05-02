/*
   --- LISTA DE EXERCÍCIOS 05 ---
    
	   https://goo.gl/FRqweY
*/

/* exercício 01:
   Liste o nome do empregado, o nome do gerente e o departamento de cada um.
*/
select Emp.NomeEmpregado,
       Dep.NomeDepartamento as NomeDepartamentoEmpregado,
       Ger.NomeEmpregado as NomeGerente,
	   DepGer.NomeDepartamento as NomeDepartamentoGerente
from Empregado Emp
left join Empregado Ger on Ger.IDEmpregado = Emp.IDGerente
left join Departamento Dep on Dep.IDDepartamento = Emp.IDDepartamento
left join Departamento DepGer on DepGer.IDDepartamento = Ger.IDDepartamento;

/* exercício 02:
   Liste o deparamento (id e nome) com o empregado de maior salário.
*/
select IDDepartamento, NomeDepartamento
from Departamento
where IDDepartamento = (select top 1 IDDepartamento
						from Empregado
						where IDDepartamento is not null
						order by Salario desc);

/* exercício 03:
   Aplique uma alteração salarial em todos os empregados que o departamento fique 
   na localidade de SAO PAULO, este reajuste deve ser de 17,3%. 
   Por segurança faça uma cópia da tabela Empregado antes de iniciar esta tarefa.
*/
select * into CopiaEmpregado from Empregado;

update e set e.Salario = (e.Salario * 1.173)
from Empregado e
join Departamento d on d.IDDepartamento = e.IDDepartamento
where d.Localizacao = 'SAO PAULO';

/* exercício 04:
   Liste todas as cidades duplicadas (nome e UF iguais).
*/
select distinct c1.Nome, c1.UF from Cidade c1
join Cidade c2 on c1.Nome like c2.Nome and c1.UF like c2.UF and c1.IDCidade != c2.IDCidade;

/* exercício 05:
   Faça uma alteraçao nas cidades que tenham nome+UF duplicados, 
   adicione no final do nome um asterisco. 
   Mas atenção! apenas a cidade com maior ID deve ser alterada.
*/
begin transaction;

update c1 set c1.Nome = c1.Nome+'*'
from Cidade c1
join Cidade c2 on c1.Nome like c2.Nome and c1.UF like c2.UF and c1.IDCidade != c2.IDCidade
where c1.IDCidade > c2.IDCidade;

commit;