using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Demo1.Dominio.Entidades
{
    public class Pedido
    {
        public int Id { get; set; }
        public string NomeCliente { get; set; }
        public List<ItemPedido> Itens { get; set; }

        public bool Validar(out List<string> mensagens)
        {
            mensagens = new List<string>();

            if (string.IsNullOrWhiteSpace(this.NomeCliente))
                mensagens.Add("Nome do Cliente deve ser informado.");
            if (this.Itens.Count < 1)
                mensagens.Add("Pedido deve ter no mínimo um item.");

            return mensagens.Count == 0;
        }
    }
}
