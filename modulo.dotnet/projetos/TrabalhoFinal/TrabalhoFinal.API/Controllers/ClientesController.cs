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
    [RoutePrefix("api/clientes")]
    public class ClientesController : ApiController
    {
        private ClienteRepositorio repo = new ClienteRepositorio();

        [HttpGet]
        public IHttpActionResult Obter()
        {
            return Ok(repo.Obter());
        }

        [HttpGet, Route("{id:int}")]
        public IHttpActionResult Obter(int id)
        {
            Cliente cli = repo.Obter(id);
            if (cli == null)
                return NotFound();
            else
                return Ok(cli);
        }

        [HttpGet, Route("{nome}")]
        public IHttpActionResult Obter(string nome)
        {
            return Ok(repo.Obter(nome));
        }

        [HttpGet, Route("{id:int}/locacoes")]
        public IHttpActionResult ObterLocacoes(int id)
        {
            return Ok(repo.ObterLocacoes(id));
        }

        [HttpPost]
        public IHttpActionResult Cadastrar(Cliente cliente)
        {
            List<string> msg = new List<string>();
            if (cliente.Validar(out msg) == false)
                return BadRequest(String.Join(" ", msg));
            else
                return Ok(repo.Criar(cliente));
        }

        [HttpPut, Route("{id:int}")]
        public IHttpActionResult Alterar(int id, Cliente cliente)
        {
            cliente.Id = id;
            if (repo.Alterar(cliente) == false)
                return BadRequest();
            else
                return Ok(cliente);
        }

        [HttpDelete, Route("{id:int}")]
        public IHttpActionResult Excluir(int id)
        {
            if (repo.Excluir(id) == false)
                return BadRequest();
            else
                return Ok();
        }
    }
}
