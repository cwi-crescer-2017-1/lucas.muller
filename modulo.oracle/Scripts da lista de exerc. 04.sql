/* --- LISTA DE EXERCICIO 04 --
       https://goo.gl/HkgVn5    */

-- exercício 01
Create table LogAposta (
  IDLogAposta   integer not null,
  IDAposta       integer not null,
  Usuario        varchar2(30),
  Data           date default sysdate,  
  Operacao       char(1) not null,
    constraint PK_LogAposta
       primary key (IDLogAposta)
);

Create sequence sqLogAposta;

create or replace
trigger TR_AUD1_APOSTA
    after insert or update or delete on APOSTA
    for each row
Declare
  v_operacao char(1);
  v_idaposta integer;
Begin

  if INSERTING then
     v_operacao := 'I';
     v_idaposta := :new.idaposta;
  elsif UPDATING then
     v_operacao := 'U';
     v_idaposta := :old.IDAposta;
  else
     v_operacao := 'D';
     v_idaposta := :old.IDAposta;
  end if;
  Insert into LogAposta (IDLogAposta, IDAposta, data, usuario, operacao)
      values (sqLogAposta.nextval, v_idaposta, sysdate, user, v_operacao);

End TR_AUD1_APOSTA;

Create table LogNumero_Aposta (
  IDLogNumero_Aposta   integer not null,
  IDNumero_Aposta      integer not null,
  IDAposta             integer not null,
  Usuario              varchar2(30),
  Data                 date default sysdate,  
  Numero_Antigo        integer,
  Numero_Novo          integer,
  Operacao             char(1) not null,
    constraint PK_LogNumero_Aposta
       primary key (IDLogNumero_Aposta)
);

Create sequence sqLogNumero_Aposta;

create or replace
trigger TR_AUD1_NUMERO_APOSTA
    after insert or update or delete on NUMERO_APOSTA
    for each row
Declare
  v_operacao char(1);
  v_idaposta integer;
  v_idnumero_aposta integer;
  v_numero_novo integer;
  v_numero_antigo integer;
Begin

  if INSERTING then
     v_operacao := 'I';
     v_idaposta := :new.idaposta;
     v_idnumero_aposta := :new.idnumero_aposta;
     v_numero_novo := :new.numero;
     v_numero_antigo := null;
  elsif UPDATING then
     v_operacao := 'U';
     v_idaposta := :old.IDAposta;
     v_idnumero_aposta := :old.idnumero_aposta;
     v_numero_novo := :new.numero;
     v_numero_antigo := :old.numero;
  else
     v_operacao := 'D';
     v_idaposta := :old.IDAposta;
     v_idnumero_aposta := :old.idnumero_aposta;
     v_numero_novo := null;
     v_numero_antigo := :old.numero;
  end if;
  Insert into LogNumero_Aposta (IDLogNumero_Aposta, IDNumero_Aposta, IDAposta, data, usuario, operacao, Numero_Antigo, Numero_Novo)
      values (sqLogNumero_Aposta.nextval, v_idnumero_aposta, v_idaposta, sysdate, user, v_operacao, v_numero_antigo, v_numero_novo);
End;

-- exercício 02
select apt.IDCONCURSO,
       cid.UF,
       count(apt.IDAPOSTA) as Apostas,
       count(pr.IDAPOSTA) as Acertadores,
       sum(apt.VALOR) as Arrecadacao,
       sum(NVL(pr.VALOR, 0)) as Premiacao
from Aposta apt
join Cidade cid on cid.IDCIDADE = apt.IDCIDADE
left join Aposta_Premiada pr on pr.IDAPOSTA = apt.IDAPOSTA
group by apt.IDCONCURSO, cid.UF
order by apt.IDCONCURSO, Apostas desc, Arrecadacao desc;

-- exercício 03
create or replace view vw_Identificar_Fraudes as
  select logApt.IDLOGAPOSTA, logApt.DATA, logApt.USUARIO, logApt.OPERACAO, apt.*, cs.Data_Sorteio
  from LogAposta logApt
  join Aposta apt on apt.IDAposta = logApt.IDAposta
  join Concurso cs on cs.IDConcurso = apt.IDConcurso
  where logApt.Data > cs.Data_Sorteio and (logApt.Operacao = 'I' or logApt.Operacao = 'U');