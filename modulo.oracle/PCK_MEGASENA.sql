create or replace package body pck_megasena is

  /* Busca valores percentuais conforme regra definida na tabela 'Regra_Rateio_Premio' */
  function buscaPercentual(pIdentificador in varchar2) return number as
        -- 
        v_percentual  regra_rateio_premio.percentual%type; -- herdará as propriedades do campo percentual
      begin
        
        -- busca percentual conforme parametro de entrada
        select percentual
        into   v_percentual   -- atribuí valor para a variavel
        from   regra_rateio_premio
        where  identificador = lower(pIdentificador);
        
        return v_percentual;
      exception
        when no_data_found then
          dbms_output.put_line('Erro: '||pIdentificador);
          raise_application_error(-20002, sqlerrm);
      end buscaPercentual;
  ---------------------------------------------------------------------------------------------------------------------------------------
  /* Executa o rateio dos premios conforme definção das regras */
  procedure defineRateioPremio (pPremio in number) as
    begin
    
       gPremio_sena          := buscaPercentual('premio_sena') * pPremio;
       gPremio_quina         := buscaPercentual('premio_quina') * pPremio;
       gPremio_quadra        := buscaPercentual('premio_quadra') * pPremio;
       gAcumulado_proximo_05 := buscaPercentual('acumulado_05') * pPremio;
       gAcumulado_final_ano  := buscaPercentual('acumulado_final_ano') * pPremio;
  
    end defineRateioPremio;

  ---------------------------------------------------------------------------------------------------------------------------------------
  /* Salva o registro referente ao concurso */
  procedure salvaConcurso (pConcurso in integer,
                           pData     in date,
                           pPremio   in number) as
    begin

       defineRateioPremio(pPremio);
       
       --insereConcurso( pConcurso, pData, gPremio_Sena, gPremio_Quina, gPremio_Quadra, gAcumulado_proximo_05, gAcumulado_final_ano );
       
       Insert into Concurso 
           (Idconcurso, Data_Sorteio, Premio_Sena, Premio_Quina, Premio_Quadra, Acumulado_Proximo_05, Acumulado_Final_Ano)
       Values 
           (pConcurso, pData, gPremio_Sena, gPremio_Quina, gPremio_Quadra, gAcumulado_proximo_05, gAcumulado_final_ano);
    end salvaConcurso;
  ---------------------------------------------------------------------------------------------------------------------------------------
    /*
     Questão "A" - implementar rotina que irá inserir um novo concurso
    */
  procedure geraProximoConcurso(vDataSorteio Concurso.Data_Sorteio%type) as
    vIDUltimoConcurso Concurso.IDConcurso%type;
    vArrecadacaoUltimoConcurso Aposta.Valor%type;
    vUltimoAcumulou Concurso.Acumulou%type;
   begin
   
        select MAX(IDConcurso) into vIDUltimoConcurso from Concurso;
        select SUM(Valor) into vArrecadacaoUltimoConcurso from Aposta where IDConcurso = vIDUltimoConcurso;
        select Acumulou into vUltimoAcumulou from Concurso where IDConcurso = vIDUltimoConcurso;
        
        IF vUltimoAcumulou = 1 THEN
            DBMS_OUTPUT.PUT_LINE('Erro: penultimo concurso acumulou e isso ainda não foi tratado.');
            return;
        END IF;
        -- falta verificar se o anterior acumulou e somar ao valor
        -- perguntar pro André como funciona
        
        PCK_MEGASENA
        .salvaConcurso(vIDUltimoConcurso + 1,
                     vDataSorteio,
                     vArrecadacaoUltimoConcurso * 0.453
                    );
        DBMS_OUTPUT.PUT_LINE('Concurso gerado com sucesso!');               
      
   end geraProximoConcurso;
  ---------------------------------------------------------------------------------------------------------------------------------------
    /*
     Questão "B" - implementar rotina que irá inserir todos os acertadores de um determinado concurso
    */
  procedure atualizaAcertadores (pConcurso in integer) as
   begin
      null; --> codar aqui
   end atualizaAcertadores;
   
begin
  -- Initialization
  null; --<Statement>;
end pck_megasena;