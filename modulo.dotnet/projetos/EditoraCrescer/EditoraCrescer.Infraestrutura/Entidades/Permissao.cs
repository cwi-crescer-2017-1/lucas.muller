using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EditoraCrescer.Infraestrutura.Entidades
{
    public class Permissao
    {
        public int Id { get; set; }
        public string Nome { get; set; }

        protected Permissao() { }

        public Permissao(string nome)
        {
            Nome = nome;
        }
    }
}
