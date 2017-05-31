using EditoraCrescer.Infraestrutura;
using EditoraCrescer.Infraestrutura.Entidades;
using EditoraCrescer.Infraestrutura.Repositorios;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace EditoraCrescer.API.Controllers
{
    public class LivrosController : ApiController
    {
        private LivroRepositorio repositorio = new LivroRepositorio();

        [HttpGet]
        public IHttpActionResult Listar()
        {
            return Ok(repositorio.Obter());
        }

        [HttpGet]
        [Route("api/Livros/{isbn}")]
        public IHttpActionResult ObterLivro(int isbn)
        {
            var livro = repositorio.Obter(isbn);
            if (livro == null) return NotFound();
            else return Ok(livro);
        }

        [HttpPost]
        public IHttpActionResult CadastrarLivro(Livro livro)
        {
            return Ok(repositorio.Criar(livro));
        }

        [HttpDelete]
        [Route("api/Livros/{isbn}")]
        public IHttpActionResult RemoverLivro(int isbn)
        {
            var result = repositorio.Excluir(isbn);
            if (result) return Ok();
            else return BadRequest();
        }
    }
}