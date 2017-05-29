using Demo1.Infraestrutura.Repositorios;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Demo1.WebApi.Controllers
{
    public class PedidosController : ApiController
    {
        private static PedidoRepositorio pedidoRepositorio = new PedidoRepositorio();

        [HttpGet]
        public IHttpActionResult Get()
        {
            return Ok(pedidoRepositorio.Listar());
        }

        [HttpGet]
        public IHttpActionResult Get(int id)
        {
            var pedido = pedidoRepositorio.Obter(id);
            if (pedido == null) return NotFound();
            else return Ok(pedido);
        }
    }
}
