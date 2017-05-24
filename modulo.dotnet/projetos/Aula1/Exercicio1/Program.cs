using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Exercicio1
{
    class Program
    {
        static void Main(string[] args)
        {
            List<int> inteiros = new List<int>();

            while (true)
            {
                string entrada = LeLinhaComMsg("Digite um valor: ");

                if (entrada.Equals("exit", StringComparison.OrdinalIgnoreCase))
                    break;

                int valor = 0;
                if(!int.TryParse(entrada, out valor)) continue;

                inteiros.Add(valor);
            }

            Console.WriteLine("\nValores inteiros digitados: ");
            Console.Write("[ ");
            foreach (var item in inteiros)
            {
                Console.Write(item + " ");
            }
            Console.WriteLine("]");

            Console.Write("\nPressione alguma tecla para finalizar o script...");
            Console.ReadKey();
        }

        private static string LeLinhaComMsg(string msg)
        {
            Console.Write(msg);
            return Console.ReadLine();
        }
    }
}
