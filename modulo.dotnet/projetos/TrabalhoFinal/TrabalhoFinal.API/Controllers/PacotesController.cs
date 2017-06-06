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
    public class PacotesController : ApiController
    {
        private PacoteRepositorio repo = new PacoteRepositorio();

        [HttpGet]
        public IHttpActionResult ListarTodos()
        {
            return Ok(repo.Obter());
        }

        [HttpGet]
        public IHttpActionResult ObterPacote(int id)
        {
            Pacote p = repo.Obter(id);
            if (p == null)
                return NotFound();
            else
                return Ok(p);
        }

        [HttpPost]
        public IHttpActionResult CriarPacote(Pacote p)
        {
            List<string> msg = null;
            if (p.Validar(out msg) == false)
                return BadRequest(String.Join(" ", msg));

            return Ok(repo.Criar(p));
        }

        [HttpPut]
        public IHttpActionResult AlterarPacote(int id, Pacote p)
        {
            p.Id = id;
            List<string> msg = null;
            if (p.Validar(out msg) == false)
                return BadRequest(String.Join(" ", msg));

            if (repo.Alterar(p) == true)
                return Ok(p);
            else
                return BadRequest("Pacote não encontrado.");
        }

        [HttpDelete]
        public IHttpActionResult RemoverPacote(int id)
        {
            if (repo.Excluir(id) == true)
                return Ok();
            else
                return BadRequest();
        }
    }
}
