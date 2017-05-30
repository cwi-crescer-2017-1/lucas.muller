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
    public class AutoresController : ApiController
    {
        private AutorRepositorio repositorio = new AutorRepositorio();

        [HttpGet]
        public IHttpActionResult Listar()
        {
            return Ok(repositorio.Obter());
        }

        [HttpGet]
        [Route("api/Autores/{id}")]
        public IHttpActionResult ObterAutor(int id)
        {
            var autor = repositorio.Obter(id);
            if (autor == null) return NotFound();
            else return Ok(autor);
        }

        [HttpGet]
        [Route("api/Autores/{id}/Livros")]
        public IHttpActionResult ObterLivros(int id)
        {
            return Ok(repositorio.ObterLivros(id));
        }

        [HttpPost]
        public IHttpActionResult CadastrarLivro(Autor autor)
        {
            return Ok(repositorio.Criar(autor));
        }

        [HttpDelete]
        [Route("api/Autores/{id}")]
        public IHttpActionResult RemoverLivro(int id)
        {
            var result = repositorio.Excluir(id);
            if (result) return Ok();
            else return BadRequest();
        }
    }
}
