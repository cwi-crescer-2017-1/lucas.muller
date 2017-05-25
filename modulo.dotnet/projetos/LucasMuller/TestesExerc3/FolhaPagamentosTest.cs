using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Exercicio3;

namespace TestesExerc3
{
    [TestClass]
    public class FolhaPagamentosTest
    {
        [TestMethod]
        public void GerarDemonstrativoComDadosDoExemplo ()
        {
            FolhaPagamento folha = new FolhaPagamento();
            Demonstrativo demonstrativo = folha.GerarDemonstrativo(200, 5000, 50, 10);

            Assert.AreEqual(5000, demonstrativo.SalarioBase); // salário bruto
            Assert.AreEqual(1250, demonstrativo.HorasExtras.ValorTotalHoras); // horas extras
            Assert.AreEqual(250, demonstrativo.HorasDescontadas.ValorTotalHoras); // horas descontadas
            Assert.AreEqual(6000, demonstrativo.TotalProventos); // total proventos
            Assert.AreEqual(600, demonstrativo.Inss.Valor); // inss valor
            Assert.AreEqual(10, demonstrativo.Inss.Aliquota); // inss aliquota
            Assert.AreEqual(1485, demonstrativo.Irrf.Valor); // irpf valor
            Assert.AreEqual(27.5, demonstrativo.Irrf.Aliquota); // irpf aliquota
            Assert.AreEqual(2085, demonstrativo.TotalDescontos); // total descontos
            Assert.AreEqual(3915, demonstrativo.TotalLiquido); // salário liquido
            Assert.AreEqual(660, demonstrativo.Fgts.Valor); // fgts
        }

        [TestMethod]
        public void GerarDemonstrativoComSalarioDeMilReais ()
        {
            FolhaPagamento folha = new FolhaPagamento();
            Demonstrativo demonstrativo = folha.GerarDemonstrativo(170, 1000, 0, 0);

            Assert.AreEqual(1000, demonstrativo.SalarioBase); // salário bruto
            Assert.AreEqual(0, demonstrativo.HorasExtras.ValorTotalHoras); // horas extras
            Assert.AreEqual(0, demonstrativo.HorasDescontadas.ValorTotalHoras); // horas descontadas
            Assert.AreEqual(1000, demonstrativo.TotalProventos); // total proventos
            Assert.AreEqual(80, demonstrativo.Inss.Valor); // inss valor
            Assert.AreEqual(8, demonstrativo.Inss.Aliquota); // inss aliquota
            Assert.AreEqual(0, demonstrativo.Irrf.Valor); // irpf valor
            Assert.AreEqual(0, demonstrativo.Irrf.Aliquota); // irpf aliquota
            Assert.AreEqual(80, demonstrativo.TotalDescontos); // total descontos
            Assert.AreEqual(920, demonstrativo.TotalLiquido); // salário liquido
            Assert.AreEqual(110, demonstrativo.Fgts.Valor); // fgts
        }
    }
}
