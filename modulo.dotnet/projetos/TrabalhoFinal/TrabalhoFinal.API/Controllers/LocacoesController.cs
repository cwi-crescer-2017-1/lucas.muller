using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using TrabalhoFinal.API.App_Start;
using TrabalhoFinal.Dominio;
using TrabalhoFinal.Infraestrutura.Repositorios;

namespace TrabalhoFinal.API.Controllers
{
    [BasicAuthorization]
    [RoutePrefix("api/locacoes")]
    public class LocacoesController : ApiController
    {
        private LocacaoRepositorio repo = new LocacaoRepositorio();

        [HttpGet]
        public IHttpActionResult Obter()
        {
            return Ok(repo.Obter());
        }

        [HttpGet]
        [Route("{id:int}")]
        public IHttpActionResult Obter(int id)
        {
            return Ok(repo.Obter(id));
        }

        [HttpGet]
        [BasicAuthorization(Roles = "Gerente")]
        [Route("relatorios/mensal/{data:datetime}")]
        public IHttpActionResult ObterRelatorioMensal(DateTime data)
        {
            if (data == null)
                return BadRequest();

            var retorno = repo.ObterRelatorioMensal(data);
            return Ok(retorno);
        }

        [HttpGet]
        [Route("relatorios/atrasos")]
        public IHttpActionResult ObterRelatorioAtrasos()
        {
            return Ok(repo.ObterRelatorioAtrasos());
        }

        [HttpPost]
        public IHttpActionResult Criar(Locacao locacao)
        {
            List<string> msg = null;
            if (VerificaLocacaoRepositorio.Verifica(locacao, out msg) == false)
                return BadRequest(String.Join(" ", msg));

            return Ok(repo.Criar(locacao));
        }

        [HttpPost]
        [Route("orcamento")]
        public IHttpActionResult GerarOrcamento(Locacao locacao)
        {
            locacao.DataLocacao = DateTime.Now;
            List<string> msg = null;
            if (VerificaLocacaoRepositorio.Verifica(locacao, out msg) == false)
                return BadRequest(String.Join(" ", msg));

            locacao.PrecoFinalPrevisto = repo.CalcularPreco(locacao, locacao.DataDevolucaoPrevista);
            return Ok(locacao);
        }

        [HttpGet, Route("{id:int}/devolver")]
        public IHttpActionResult DevolverProduto(int id)
        {
            if (repo.Devolver(id) == true)
                return Ok();
            else
                return NotFound();
        }

        [HttpDelete]
        [Route("{id:int}")]
        public IHttpActionResult Remover(int id)
        {
            if (repo.Remover(id) == true)
                return Ok();
            else
                return NotFound();
        }
    }
}
