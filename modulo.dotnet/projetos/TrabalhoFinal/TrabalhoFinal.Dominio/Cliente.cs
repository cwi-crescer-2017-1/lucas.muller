using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TrabalhoFinal.Dominio
{
    public class Cliente
    {
        public int Id { get; set; }
        public string Nome { get; set; }
        public string Endereco { get; set; }
        public string Cpf { get; set; }
        public Genero Genero { get; set; }
        public DateTime DataNascimento { get; set; }

        protected Cliente() { }

        public bool Validar(out List<string> Mensagens)
        {
            Mensagens = new List<string>();

            if (string.IsNullOrWhiteSpace(Nome))
                Mensagens.Add("Nome é inválido.");

            if (string.IsNullOrWhiteSpace(Endereco))
                Mensagens.Add("Endereço é inválido.");

            if (Cpf == null || Cpf.Length != 11)
                Mensagens.Add("CPF inválido.");

            return Mensagens.Count == 0;
        }
    }
}
