using Chat.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Chat.Controllers
{
    public class MensagensController : ApiController
    {
        private static List<Mensagem> Mensagens = new List<Mensagem>();

        [HttpGet]
        public IHttpActionResult ObterMensagens(string order = null, int newerThan = 0, int limit = 0)
        {
            List<Mensagem> filtro = null;
            if (order == "desc") filtro = (Mensagens.OrderByDescending(msg => msg.EnviadoEm).ToList());
            else filtro = Mensagens.OrderBy(msg => msg.EnviadoEm).ToList();

            if (newerThan != 0) filtro = filtro.Where(msg => msg.Id > newerThan).ToList();
            if (limit > 0) filtro = filtro.Take(limit).ToList();
            return Ok(filtro);
        }

        [HttpGet]
        public IHttpActionResult ObterMensagem(int id)
        {
            var filtro = Mensagens.Where(msg => msg.Id == id).ToList();
            if (filtro.Count() == 0)
                return NotFound();
            else
                return Ok(filtro.First());
        }

        [HttpPost]
        public IHttpActionResult CadastrarMensagem(Mensagem mensagem)
        {
            if (mensagem == null)
                return BadRequest();

            var usuarioAutorExiste = UsuariosController.Usuarios.FindIndex(usu => usu.Id == mensagem.IdUsuarioAutor) >= 0;
            if (usuarioAutorExiste)
            {
                mensagem.Id = ++Mensagem.UltimoID;
                Mensagens.Add(mensagem);
                return Ok(mensagem);
            }
            else
            {
                return BadRequest();
            }
        }

        [HttpPut]
        public IHttpActionResult AlterarMensagem(int id, Mensagem mensagem)
        {
            var index = Mensagens.FindIndex(msg => msg.Id == id);

            if (index < 0)
            {
                return NotFound();
            }
            else
            {
                var mensagemLista = Mensagens[index];
                mensagemLista.Texto = mensagem.Texto;
                return Ok(mensagemLista);
            }
        }
    }
}
