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
    public class OpcionaisController : ApiController
    {
        private OpcionalRepositorio repo = new OpcionalRepositorio();

        [HttpGet]
        public IHttpActionResult ListarTodos()
        {
            return Ok(repo.Obter());
        }

        [HttpGet]
        public IHttpActionResult ObterPacote(int id)
        {
            Opcional p = repo.Obter(id);
            if (p == null)
                return NotFound();
            else
                return Ok(p);
        }

        [HttpPost]
        public IHttpActionResult Criar(Opcional op)
        {
            List<string> msg = null;
            if (op.Validar(out msg) == false)
                return BadRequest(String.Join(" ", msg));

            return Ok(repo.Criar(op));
        }

        [HttpPut]
        public IHttpActionResult Alterar(int id, Opcional op)
        {
            op.Id = id;
            List<string> msg = null;
            if (op.Validar(out msg) == false)
                return BadRequest(String.Join(" ", msg));

            if (repo.Alterar(op) == true)
                return Ok(op);
            else
                return BadRequest("Pacote não encontrado.");
        }

        [HttpDelete]
        public IHttpActionResult Remover(int id)
        {
            if (repo.Excluir(id) == true)
                return Ok();
            else
                return BadRequest();
        }
    }
}
