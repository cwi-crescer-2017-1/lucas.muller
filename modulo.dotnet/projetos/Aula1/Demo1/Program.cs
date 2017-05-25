using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Demo1
{
    class Program
    {
        public static void Main(string[] args)
        {
            double peso = LeDoubleComMsg("Digite seu peso em kg: ");
            double altura = LeDoubleComMsg("Digite sua altura em m: ");

            IMC imc = new Demo1.IMC(peso, altura);
            Console.WriteLine($"Seu IMC é de aproximadamente {Math.Round(imc.CalculaIMC(), 2)}.");

            Console.WriteLine("-----------------------------------");

            Pessoa pessoa = new Demo1.Pessoa();
            // var pessoa2 = new Pessoa();

            pessoa.Id = 1;
            pessoa.Nome = "Lucas Müller";
            pessoa.DataNascimento = new DateTime(1999, 10, 27);

            pessoa.ExibeDados();

            Console.Write("\nPressione alguma tecla para finalizar o script...");
            Console.ReadKey();
        }

        private static double LeDoubleComMsg(string msg)
        {
            bool resultadoTryParse = false;
            double resultado = 0;
            while (resultadoTryParse == false)
            {
                string entrada = LeLinhaComMsg(msg);
                resultadoTryParse = double.TryParse(entrada, out resultado);
            }
            return resultado;
        }

        private static string LeLinhaComMsg(string msg)
        {
            Console.Write(msg);
            return Console.ReadLine();
        }
    }
}
