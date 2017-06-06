using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using TrabalhoFinal.Infraestrutura.Repositorios;
using TrabalhoFinal.Dominio;
using TrabalhoFinal.API.App_Start;

namespace TrabalhoFinal.API.Controllers
{
    [BasicAuthorization]
    public class ProdutosController : ApiController
    {
        private ProdutoRepositorio repo = new ProdutoRepositorio();

        [HttpGet]
        public IHttpActionResult Obter()
        {
            return Ok(repo.Obter());
        }

        [HttpGet]
        public IHttpActionResult Obter(int id)
        {
            Produto p = repo.Obter(id);
            if (p == null)
                return NotFound();
            else
                return Ok(p);
        }

        [HttpPost]
        public IHttpActionResult Criar(Produto p)
        {
            List<string> msg = null;
            if (p.Validar(out msg) == false)
                return BadRequest(String.Join(" ", msg));

            return Ok(repo.Criar(p));
        }

        [HttpPut]
        public IHttpActionResult Alterar(int id, Produto p)
        {
            p.Id = id;
            List<string> msg = null;
            if (p.Validar(out msg) == false)
                return BadRequest(String.Join(" ", msg));

            if (repo.Alterar(p) == true)
                return Ok(p);
            else
                return BadRequest("Produto não encontrado.");
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
