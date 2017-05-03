/*
   --- LISTA DE EXERCÍCIOS 04 ---
       
	   https://goo.gl/OQgKfb
*/

/* exercício 01:
   Liste todos os projetos que tiveram atrasos no início da obra, 
   exibir também o tempo previsto (em meses), e o tempo realizado (em meses) para a conclusão da obra.
*/
select Identificador, Projeto, Data_Inicio_Prev, Data_Inicio_Real, Data_Fim_Prev, Data_Fim_Real,
       DATEDIFF(month, Data_Inicio_Prev, Data_Fim_Prev) as TempoPrevisto,
	   DATEDIFF(month, Data_Inicio_Real, Data_Fim_Real) as TempoRealizado
from Licitacao
where Data_Inicio_Real > Data_Inicio_Prev;

/* exercício 02:
   Liste as TOP 10 empresas que tiveram maior faturamento, 
   deve ser projetado também o valor médio por profissional.
*/
select top 10 Empresa_Licitante, 
       SUM(Valor_Realizado) as Faturamento,
	   SUM(Valor_Realizado)/SUM(Profissionais) as ValorMedioPorProfissional
from Licitacao
group by Empresa_Licitante
order by Faturamento desc;

/* exercício 03:
   Liste as TOP 10 cidades com maior arrecação de impostos.
*/
select Municipio, SUM(Imposto_Municipal) as Arrecadacao
from Licitacao
group by Municipio
order by Arrecadacao desc;

/* exercício 04:
   Liste todos os projetos que tiveram a data de início (real) ocorrendo 
   em finais de semana (sábado ou domingo).
*/
select * from Licitacao
where DATEPART(dw, Data_Inicio_Real) in (1, 7); -- 1 é domingo e 7 é sábado

/* exercício 05:
   Liste todas as empresas que tiveram (ou terão) um faturamento (valor previsto) 
   superior a 50% do seu faturamento no ano anterior.
*/
select Empresa_Licitante,
       SUM(Valor_Previsto) as FaturamentoPrevisto,
	   Faturamento_1Ano_Anterior
from Licitacao
group by Empresa_Licitante, Faturamento_1Ano_Anterior
having SUM(Valor_Previsto) > (Faturamento_1Ano_Anterior * 0.5);

/* exercício 06:
   Liste os projetos exibindo o custo real do projeto. 
   Caso o projeto seja de esfera federal deve ser descontado o imposto federal, 
   e assim respectivamente para as esferas estaduais e municipais.
*/
select Identificador, Projeto,
       Esfera, Valor_Realizado,
	   CASE Esfera 
	        WHEN 'Federal' THEN Imposto_Federal 
			WHEN 'Estadual' THEN Imposto_Estadual
			WHEN 'Municipal' THEN Imposto_Municipal
	   END AS Imposto,
	   CASE Esfera 
	        WHEN 'Federal' THEN Valor_Realizado - Imposto_Federal 
			WHEN 'Estadual' THEN Valor_Realizado - Imposto_Estadual
			WHEN 'Municipal' THEN Valor_Realizado - Imposto_Municipal
	   END AS Valor_Real 
from Licitacao;

/* exercício 07:
   O projeto 17255 foi suspenso, o motivo foi o elevado custo 
   para o orçamento anual do município. A partir de uma investigação mais detalhada 
   foi visto que esse motivo não era a verdadeira razão. 
   Explique por que este motivo não é válido apresentando uma consulta com dados.
*/
select Identificador, Projeto, Situacao, Valor_Realizado, Imposto_Municipal, 
       (Valor_Realizado - Imposto_Municipal) as Valor_Real
from Licitacao where Projeto = 'Escola Técnica Bento Gonçalves';
-- projeto superfaturado? projeto fake?