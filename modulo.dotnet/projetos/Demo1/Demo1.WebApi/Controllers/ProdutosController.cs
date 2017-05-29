using Demo1.Dominio.Entidades;
using Demo1.Infraestrutura.Repositorios;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Demo1.WebApi.Controllers
{
    public class ProdutosController : ApiController
    {
        private static ProdutoRepositorio produtoRepositorio = new ProdutoRepositorio();

        public IHttpActionResult Get()
        {
            return Ok(produtoRepositorio.Listar());
        }

        [HttpGet]
        public IHttpActionResult Get(int id)
        {
            var produto = produtoRepositorio.Obter(id);
            if (produto == null) return NotFound();
            else return Ok(produto);
        }

        public IHttpActionResult Post(Produto produto)
        {
            // valida produto
            var mensagens = new List<string>();
            if (!produto.Validar(out mensagens))
                return BadRequest(string.Join(" ", mensagens));

            // insere produto
            produtoRepositorio.Criar(produto);
            return Ok(produto);
        }

        public IHttpActionResult Put(int id, Produto produto)
        {
            // valida produto
            var mensagens = new List<string>();
            if (!produto.Validar(out mensagens))
                return BadRequest(string.Join(" ", mensagens));

            // insere produto
            produto.Id = id;
            produtoRepositorio.Alterar(produto);
            return Ok(produto);
        }

        public IHttpActionResult Delete(int id)
        {
            produtoRepositorio.Deletar(id);
            return Ok();
        }
    }
}
