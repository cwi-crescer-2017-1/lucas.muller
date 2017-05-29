using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Demo1.Dominio.Entidades
{
    public class Produto
    {
        public int Id { get; set; }
        public string Nome { get; set; }
        public decimal Preco { get; set; }
        public int Estoque { get; set; }

        public bool Validar(out List<string> mensagens)
        {
            mensagens = new List<string>(); 

            if (string.IsNullOrWhiteSpace(this.Nome))
                mensagens.Add("Nome do produto deve ser informado.");
            if (this.Preco < 0)
                mensagens.Add("Preço deve ser maior que zero.");
            if (this.Estoque < 1)
                mensagens.Add("Estoque deve ser maior que zero.");

            return mensagens.Count == 0;
        }
    }
}