using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Demo1
{
    public class IMC
    {
        public double Peso { get; set; }
        public double Altura { get; set; }

        public IMC(double peso, double altura)
        {
            this.Peso = peso;
            this.Altura = altura;
        }

        public double CalculaIMC()
        {
            return this.Peso / Math.Pow(this.Altura, 2);
        }
    }
}
