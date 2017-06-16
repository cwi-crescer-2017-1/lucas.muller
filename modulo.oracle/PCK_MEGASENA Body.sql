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
    vValorAcumulado Concurso.Premio_Sena%type := 0;
   begin
   
        select MAX(IDConcurso) into vIDUltimoConcurso from Concurso;
        select SUM(Valor) into vArrecadacaoUltimoConcurso from Aposta where IDConcurso = vIDUltimoConcurso;
        select Acumulou into vUltimoAcumulou from Concurso where IDConcurso = vIDUltimoConcurso;
        
        IF vUltimoAcumulou = 1 THEN
            select Premio_Sena into vValorAcumulado from Concurso where IDConcurso = vIDUltimoConcurso;
        END IF;
        
        PCK_MEGASENA
        .salvaConcurso(vIDUltimoConcurso + 1,
                     vDataSorteio,
                     (vArrecadacaoUltimoConcurso + vValorAcumulado) * 0.453
                    );
        DBMS_OUTPUT.PUT_LINE('Concurso gerado com sucesso!');               
      
   end geraProximoConcurso;
  --------------------------------------------------------------------------------------------------------------------------------------
    /*
     Questão "B" - implementar rotina que irá inserir todos os acertadores de um determinado concurso
    */
  procedure atualizaAcertadores (pConcurso in integer) as
      CURSOR vGanhadores IS
        select *
        from 
          (select apt.IDAposta,
               COUNT(CASE WHEN 
                       numApt.Numero = co.Primeira_Dezena or 
                       numApt.Numero = co.Segunda_Dezena or
                       numApt.Numero = co.Terceira_Dezena or
                       numApt.Numero = co.Quarta_Dezena or
                       numApt.Numero = co.Quinta_Dezena or
                       numApt.Numero = co.Sexta_Dezena
                     THEN 1 END) as Acertos
          from Aposta apt
          join Concurso co on co.IDConcurso = apt.IDConcurso
          join Numero_Aposta numApt on numApt.IDAposta = apt.IDAposta
          where co.IDConcurso = pConcurso
          group by apt.IDAposta)
        where Acertos >= 4
        order by Acertos desc, IDAposta;
      vQuantGanhadores integer := 0;  
      vGanhadoresQuadra integer := 0;
      vGanhadoresQuina integer := 0;
      vGanhadoresSena integer := 0;
      vPremioQuadra Concurso.Premio_Quadra%type;
      vPremioQuina Concurso.Premio_Quina%type;
      vPremioSena Concurso.Premio_Sena%type;
      vUltimoIDApostaPremiada integer;
      vAcumulou number(1) := 1;
      vPremioGanhador Aposta_Premiada.Valor%type;
   begin
      -- conta ganhadores de cada prêmio
      FOR Ganhador IN vGanhadores LOOP
        IF (Ganhador.Acertos = 6) THEN
          vAcumulou := 0;
          vGanhadoresSena := vGanhadoresSena + 1;
        ELSIF (Ganhador.Acertos = 5) THEN
          vGanhadoresQuina := vGanhadoresQuina + 1;
        ELSIF (Ganhador.Acertos = 4) THEN
          vGanhadoresQuadra := vGanhadoresQuadra + 1;
        END IF;
        
        vQuantGanhadores := vQuantGanhadores + 1;
      END LOOP;
      
      -- se acumulou, atualiza o concurso
      IF vAcumulou = 1 THEN
        UPDATE Concurso SET Acumulou = 1 WHERE IDConcurso = pConcurso;
      END IF;
      
      -- calcula o valor de cada aposta
      SELECT (Premio_Quadra / CASE WHEN vGanhadoresQuadra = 0 THEN 1 WHEN vGanhadoresQuadra <> 0 THEN vGanhadoresQuadra END) INTO vPremioQuadra FROM Concurso WHERE IDConcurso = pConcurso;
      SELECT (Premio_Quina / CASE WHEN vGanhadoresQuina = 0 THEN 1 WHEN vGanhadoresQuina <> 0 THEN vGanhadoresQuina END) INTO vPremioQuina FROM Concurso WHERE IDConcurso = pConcurso;
      SELECT (Premio_Sena / CASE WHEN vGanhadoresSena = 0 THEN 1 WHEN vGanhadoresSena <> 0 THEN vGanhadoresSena END) INTO vPremioSena FROM Concurso WHERE IDConcurso = pConcurso;
      SELECT COALESCE(max(IDAposta_Premiada), 0) INTO vUltimoIDApostaPremiada FROM Aposta_Premiada;
      
      -- insere no banco as apostas premiadas
      FOR Ganhador IN vGanhadores LOOP
        IF (Ganhador.Acertos = 6) THEN
          vPremioGanhador := vPremioSena;
        ELSIF (Ganhador.Acertos = 5) THEN
          vPremioGanhador := vPremioQuina;
        ELSIF (Ganhador.Acertos = 4) THEN
          vPremioGanhador := vPremioQuadra;
        END IF;
        vUltimoIDApostaPremiada := vUltimoIDApostaPremiada + 1;
        INSERT INTO Aposta_Premiada (IDAposta_Premiada, IDAposta, Acertos, Valor)
        VALUES (vUltimoIDApostaPremiada, Ganhador.IDAposta, Ganhador.Acertos, vPremioGanhador);
      END LOOP;
   end atualizaAcertadores;
   
begin
  -- Initialization
  null; --<Statement>;
end pck_megasena;