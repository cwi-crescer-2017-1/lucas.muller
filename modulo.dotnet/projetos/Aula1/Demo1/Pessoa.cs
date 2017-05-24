using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Demo1
{
    public class Pessoa
    {
        public int Id { get; set; }
        public string Nome { get; set; }
        public DateTime DataNascimento { get; set; }
        public void ExibeDados()
        {
            Console.WriteLine("ID: " + this.Id);
            // interpolation: Console.WriteLine($"ID: {this.Id}");
            Console.WriteLine("Nome: " + this.Nome);
            Console.WriteLine("Data de Nascimento: " + this.DataNascimento.ToString("dd/MM/yyyy"));
        }
    }
}
