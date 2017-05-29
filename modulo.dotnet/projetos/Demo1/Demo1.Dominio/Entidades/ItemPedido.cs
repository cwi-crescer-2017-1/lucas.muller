using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Demo1.Dominio.Entidades
{
    public class ItemPedido
    {
        public int Id { get; set; }
        public int ProdutoId { get; set; }
        public int Quantidade { get; set; }

        public bool Validar(out List<string> mensagens)
        {
            mensagens = new List<string>();

            if (this.Quantidade < 1)
            {
                mensagens.Add("Quantidade deve ser maior que zero.");
            }

            return mensagens.Count == 0;
        }
    }
}