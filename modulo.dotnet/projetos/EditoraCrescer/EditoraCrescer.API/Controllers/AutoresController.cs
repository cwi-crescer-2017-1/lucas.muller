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
    [RoutePrefix("api/Autores")]
    public class AutoresController : ApiController
    {
        private AutorRepositorio repositorio = new AutorRepositorio();

        [HttpGet]
        public IHttpActionResult Listar()
        {
            return Ok(repositorio.Obter());
        }

        [HttpGet]
        public IHttpActionResult ObterAutor(int id)
        {
            var autor = repositorio.Obter(id);
            if (autor == null) return NotFound();
            else return Ok(autor);
        }

        [HttpGet]
        [Route("{id:int}/Livros")]
        public IHttpActionResult ObterLivrosDoAutor(int id)
        {
            return Ok(repositorio.ObterLivros(id));
        }

        [HttpPost]
        public IHttpActionResult CadastrarAutor(Autor autor)
        {
            return Ok(repositorio.Criar(autor));
        }

        [HttpPut]
        public IHttpActionResult AlterarAutor(int id, Autor autor)
        {
            autor.Id = id;
            repositorio.Alterar(autor);
            return Ok(autor);
        }

        [HttpDelete]
        public IHttpActionResult RemoverAutor(int id)
        {
            var result = repositorio.Excluir(id);
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
