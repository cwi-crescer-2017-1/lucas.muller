using EditoraCrescer.Infraestrutura.Entidades;
using EditoraCrescer.Infraestrutura.Repositorios;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;

namespace EditoraCrescer.API.Controllers
{
    [RoutePrefix("api/Revisores")]
    public class RevisoresController : ApiController
    {
        private RevisorRepositorio repositorio = new RevisorRepositorio();

        [HttpGet]
        public IHttpActionResult Listar()
        {
            return Ok(repositorio.Obter());
        }

        [HttpGet]
        public IHttpActionResult ObterRevisor(int id)
        {
            var revisor = repositorio.Obter(id);
            if (revisor == null) return NotFound();
            else return Ok(revisor);
        }

        [HttpPost]
        public IHttpActionResult CadastrarRevisor(Revisor revisor)
        {
            return Ok(repositorio.Criar(revisor));
        }

        [HttpPut]
        public IHttpActionResult AlterarRevisor(int id, Revisor revisor)
        {
            revisor.Id = id;
            if (repositorio.Atualizar(revisor)) return Ok(revisor);
            else return NotFound();
        }

        [HttpDelete]
        public IHttpActionResult RemoverRevisor(int id)
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