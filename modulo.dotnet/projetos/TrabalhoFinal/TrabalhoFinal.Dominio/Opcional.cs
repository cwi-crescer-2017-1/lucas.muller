using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TrabalhoFinal.Dominio
{
    public class Opcional
    {
        public int Id { get; set; }
        public string Descricao { get; set; }
        public double Preco { get; set; }
        public int? Quantidade { get; set; }

        public bool Validar(out List<string> Mensagens)
        {
            Mensagens = new List<string>();

            if (string.IsNullOrWhiteSpace(Descricao))
                Mensagens.Add("Descrição é inválida.");

            if (Preco <= 0)
                Mensagens.Add("Preço deve ser maior que zero.");

            return Mensagens.Count == 0;
        }
    }
}
