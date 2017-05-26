using Chat.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Chat.Controllers
{
    public class UsuariosController : ApiController
    {
        internal static List<Usuario> Usuarios = new List<Usuario>();

        [HttpGet]
        public IHttpActionResult ObterUsuarios()
        {
            return Ok(Usuarios);
        }

        [HttpGet]
        public IHttpActionResult ObterUsuario(int id)
        {
            var filtro = Usuarios.Where(usu => usu.Id == id);
            if (filtro.Count() == 0)
                return NotFound();
            else
                return Ok(filtro.First());
        }

        [HttpPost]
        public IHttpActionResult CadastrarUsuario(Usuario usuario)
        {
            if (usuario == null)
                return BadRequest();

            usuario.Id = ++Usuario.UltimoID;
            Usuarios.Add(usuario);
            return Ok(usuario);
        }

        [HttpPut]
        public IHttpActionResult AlterarUsuario(int id, Usuario usuario)
        {
            if (usuario == null)
                return BadRequest();

            var index = Usuarios.FindIndex(usu => usu.Id == id);
            if (index < 0)
            {
                return NotFound();
            }
            else
            {
                usuario.Id = id;
                Usuarios[index] = usuario;
                return Ok(usuario);
            }
        }
    }
}