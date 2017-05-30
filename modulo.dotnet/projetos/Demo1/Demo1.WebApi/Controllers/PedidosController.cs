using Demo1.Dominio.Entidades;
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

        public IHttpActionResult Post(Pedido pedido)
        {
            // valida pedido
            var mensagens = new List<string>();
            if (!pedido.Validar(out mensagens))
                return BadRequest(string.Join(" ", mensagens));
            foreach (var item in pedido.Itens)
            {
                if(!item.Validar(out mensagens))
                    return BadRequest(string.Join(" ", mensagens));
            }

            try
            {
                // insere pedido
                pedidoRepositorio.Criar(pedido);
                return Ok(pedido);
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        public IHttpActionResult Put(int id, Pedido pedido)
        {
            // valida pedido
            var mensagens = new List<string>();
            if (!pedido.Validar(out mensagens))
                return BadRequest(string.Join(" ", mensagens));
            foreach (var item in pedido.Itens)
            {
                if (!item.Validar(out mensagens))
                    return BadRequest(string.Join(" ", mensagens));
            }

            try
            {
                // insere pedido
                pedido.Id = id;
                pedidoRepositorio.Alterar(pedido);
                return Ok(pedido);
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        public IHttpActionResult Delete(int id)
        {
            pedidoRepositorio.Excluir(id);
            return Ok();
        }
    }
}
