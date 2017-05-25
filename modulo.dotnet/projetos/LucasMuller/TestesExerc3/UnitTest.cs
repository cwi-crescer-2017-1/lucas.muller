using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Exercicio3;

namespace TestesExerc3
{
    [TestClass]
    public class UnitTest
    {
        [TestMethod]
        public void TesteSalarioExemplo ()
        {
            FolhaPagamento folha = new FolhaPagamento();
            Demonstrativo demonstrativo = folha.GerarDemonstrativo(200, 5000, 50, 10);
            Assert.AreEqual(1250, demonstrativo.HorasExtras.ValorTotalHoras);
            Assert.AreEqual(250, demonstrativo.HorasDescontadas.ValorTotalHoras);
            Assert.AreEqual(6000, demonstrativo.TotalProventos);
            Assert.AreEqual(2085, demonstrativo.TotalDescontos);
            Assert.AreEqual(3915, demonstrativo.TotalLiquido);
            Assert.AreEqual(660, demonstrativo.Fgts.Valor);
        }
    }
}
