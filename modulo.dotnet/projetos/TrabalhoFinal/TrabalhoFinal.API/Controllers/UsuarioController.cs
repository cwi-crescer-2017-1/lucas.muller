using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading;
using System.Web;
using System.Web.Http;
using TrabalhoFinal.API.App_Start;
using TrabalhoFinal.API.Models;
using TrabalhoFinal.Infraestrutura.Entidades;
using TrabalhoFinal.Infraestrutura.Repositorios;

namespace TrabalhoFinal.API.Controllers
{
    [AllowAnonymous]
    [RoutePrefix("api/acessos")]
    public class UsuarioController : ApiController
    {
        readonly UsuarioRepositorio repositorio;

        public UsuarioController()
        {
            repositorio = new UsuarioRepositorio();
        }

        [HttpPost, Route("registrar")]
        public IHttpActionResult Registrar([FromBody]RegistrarUsuarioModel model)
        {
            if (repositorio.Obter(model.Email) == null)
            {
                var usuario = new Usuario(model.Nome, model.Email, model.Senha);

                List<string> mensagens = null;
                if (usuario.Validar(out mensagens))
                {
                    repositorio.Criar(usuario);
                }
                else
                {
                    return BadRequest(String.Join(" ", mensagens));
                }
            }
            else
            {
                return BadRequest("Usuário já existe.");
            }

            return Ok();
        }

        [BasicAuthorization]
        [HttpGet, Route("usuario")]
        public IHttpActionResult Obter()
        {
            // só pode obter as informações do usuário corrente
            return Ok(repositorio.Obter(Thread.CurrentPrincipal.Identity.Name));
        }
    }
}