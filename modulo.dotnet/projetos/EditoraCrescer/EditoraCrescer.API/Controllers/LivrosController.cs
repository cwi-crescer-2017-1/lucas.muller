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
    [RoutePrefix("api/Livros")]
    public class LivrosController : ApiController
    {
        private LivroRepositorio repositorio = new LivroRepositorio();

        [HttpGet]
        public IHttpActionResult Listar()
        {
            return Ok(repositorio.ObterFormaResumida());
        }

        [HttpGet]
        public IHttpActionResult Listar(int limit, int page)
        {
            return Ok(repositorio.ObterFormaResumidaComPaginacao(limit, page));   
        }

        [HttpGet]
        [Route("{isbn:int}")]
        public IHttpActionResult ObterLivro(int isbn)
        {
            var livro = repositorio.Obter(isbn);
            if (livro == null) return NotFound();
            else return Ok(livro);
        }

        [HttpGet]
        [Route("Lancamentos")]
        public IHttpActionResult ObterLancamentos()
        {
            return Ok(repositorio.ObterLancamentosFormaResumida());
        }

        [HttpGet]
        [Route("Lancamentos")]
        public IHttpActionResult ObterLancamentos(int limit)
        {
            return Ok(repositorio.ObterLancamentosFormaResumida(limit));
        }

        [HttpGet]
        [Route("{genero}")]
        public IHttpActionResult ObterLivrosPorGenero(string genero)
        {
            return Ok(repositorio.ObterPorGeneroFormaResumida(genero));
        }

        [HttpPost]
        public IHttpActionResult CadastrarLivro(Livro livro)
        {
            return Ok(repositorio.Criar(livro));
        }

        [HttpPut]
        [Route("{isbn:int}")]
        public IHttpActionResult AlterarLivro(int isbn, Livro livro)
        {
            livro.Isbn = isbn;
            if (repositorio.Atualizar(livro)) return Ok(livro);
            else return NotFound();
        }

        [HttpDelete]
        [Route("{isbn}")]
        public IHttpActionResult RemoverLivro(int isbn)
        {
            var result = repositorio.Excluir(isbn);
            if (result) return Ok();
            else return BadRequest();
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
                repositorio.Dispose();

            base.Dispose(disposing);
        }
    }
}