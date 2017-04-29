/*
   --- LISTA DE EXERCÍCIO 03 ---

       https://goo.gl/y3lpOB
*/

/* exercício 01:
   Faça uma consulta que liste o total de empregados admitidos no ano de 1980. 
   Deve ser projetado nesta consulta: ID, Nome e Idade no momento da admissão.
*/
select IDEmpregado, NomeEmpregado, DATEDIFF(year, DataNascimento, DataAdmissao) as IdadeQuandoAdmitido
from Empregado
where datepart(year, DataAdmissao) = 1980;

/* exercício 02:
   Qual o cargo (tabela empregado) possui mais empregados ? Deve ser projetado apenas um registro. 
   ** DICA: Pesquise recursos específicos para esta funcionalidade.
*/
select top 1 Cargo, count(1) as Empregados
from Empregado
group by Cargo
order by Empregados desc;

/* exercício 03:
   Liste os estados (atributo UF) e o total de cidades existente em cada estado na tabela cidade.
*/
select UF, count(1) as Cidades
from Cidade
group by UF;

/* exercício 04:
   Crie um novo departamento, denominado 'Inovação' com localização em 'SÃO LEOPOLDO'. 
   Todos os empregados que foram admitidos no mês de dezembro (qualquer ano) 
   que não ocupam o cargo de 'Atendente' devem ser ter o departamento (IDDepartamento) atualizado 
   para este novo registro (inovação).
*/
BEGIN TRANSACTION

insert into Departamento (IDDepartamento, NomeDepartamento, Localizacao) 
            values (70, 'Inovação', 'SAO LEOPOLDO');

update Empregado set IDDepartamento = 70
       where Cargo != 'Atendente' and DATEPART(MONTH, DataAdmissao) = 12;

COMMIT
ROLLBACK

select * from Empregado where Cargo != 'Atendente' and DATEPART(MONTH, DataAdmissao) = 12;