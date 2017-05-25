using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repositorio
{
    public class Cargo
    {
        public string Titulo { get; private set; }
        public double Salario { get; private set; }

        public Cargo(string titulo, double salario)
        {
            this.Titulo = titulo;
            this.Salario = salario;
        }

        public override bool Equals(object obj)
        {
            if(obj == null || obj.GetType() != this.GetType())
            {
                return false;
            }

            Cargo cargoComp = (Cargo)obj;

            return this.Titulo == cargoComp.Titulo
                && this.Salario == cargoComp.Salario;
        }
    }
}
