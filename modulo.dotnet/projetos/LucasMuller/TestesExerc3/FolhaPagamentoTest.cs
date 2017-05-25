using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Exercicio3;

namespace TestesExerc3
{
    [TestClass]
    public class FolhaPagamentoTest
    {
        public const double MargemErro = 0.01;

        [TestMethod]
        public void Validar_Demonstrativo_Industria_Salario_1000()
        {
            int horasCategoria = 200;
            double salarioBase = 1000;
            double horasExtras = 0;
            double horasDescontadas = 0;

            var folhaPagamento = new FolhaPagamento();
            var demonstrativo = folhaPagamento.GerarDemonstrativo(horasCategoria, salarioBase, horasExtras, horasDescontadas);

            var quantidadeHorasExtrasEsperado = 0;
            var valorTotalHorasExtrasEsperado = 0;
            var quantidadeHorasDescontadasEsperado = 0;
            var valorTotalHorasDescontadasEsperado = 0;
            var totalProventosEsperado = 1000;
            var inssAliquotaEsperado = 8;
            var inssValorEsperado = 80;
            var irrfAliquotaEsperado = 0;
            var irrfValorEsperado = 0;
            var totalDescontosEsperado = 80;
            var totalLiquidoEsperado = 920;
            var fgtsAliquotaEsperado = 11;
            var fgtsValorEsperado = 110;

            Assert.AreEqual(demonstrativo.SalarioBase, salarioBase, MargemErro);
            Assert.AreEqual(demonstrativo.HorasExtras.QtdHoras, quantidadeHorasExtrasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasExtras.ValorTotalHoras, valorTotalHorasExtrasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasDescontadas.QtdHoras, quantidadeHorasDescontadasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasDescontadas.ValorTotalHoras, valorTotalHorasDescontadasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalProventos, totalProventosEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Inss.Aliquota, inssAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Inss.Valor, inssValorEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Irrf.Aliquota, irrfAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Irrf.Valor, irrfValorEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalDescontos, totalDescontosEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalLiquido, totalLiquidoEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Fgts.Aliquota, fgtsAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Fgts.Valor, fgtsValorEsperado, MargemErro);
        }

        [TestMethod]
        public void Validar_Demonstrativo_Industria_Salario_139944()
        {
            int horasCategoria = 200;
            double salarioBase = 1399.44;
            double horasExtras = 0;
            double horasDescontadas = 0;

            var folhaPagamento = new FolhaPagamento();
            var demonstrativo = folhaPagamento.GerarDemonstrativo(horasCategoria, salarioBase, horasExtras, horasDescontadas);

            var quantidadeHorasExtrasEsperado = 0;
            var valorTotalHorasExtrasEsperado = 0;
            var quantidadeHorasDescontadasEsperado = 0;
            var valorTotalHorasDescontadasEsperado = 0;
            var totalProventosEsperado = 1399.44;
            var inssAliquotaEsperado = 9;
            var inssValorEsperado = 125.95;
            var irrfAliquotaEsperado = 0;
            var irrfValorEsperado = 0;
            var totalDescontosEsperado = 125.95;
            var totalLiquidoEsperado = 1273.5;
            var fgtsAliquotaEsperado = 11;
            var fgtsValorEsperado = 153.93;

            Assert.AreEqual(demonstrativo.SalarioBase, salarioBase, MargemErro);
            Assert.AreEqual(demonstrativo.HorasExtras.QtdHoras, quantidadeHorasExtrasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasExtras.ValorTotalHoras, valorTotalHorasExtrasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasDescontadas.QtdHoras, quantidadeHorasDescontadasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasDescontadas.ValorTotalHoras, valorTotalHorasDescontadasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalProventos, totalProventosEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Inss.Aliquota, inssAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Inss.Valor, inssValorEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Irrf.Aliquota, irrfAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Irrf.Valor, irrfValorEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalDescontos, totalDescontosEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalLiquido, totalLiquidoEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Fgts.Aliquota, fgtsAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Fgts.Valor, fgtsValorEsperado, MargemErro);
        }

        [TestMethod]
        public void Validar_Demonstrativo_Industria_Salario_161287()
        {
            int horasCategoria = 200;
            double salarioBase = 1612.87;
            double horasExtras = 0;
            double horasDescontadas = 0;

            var folhaPagamento = new FolhaPagamento();
            var demonstrativo = folhaPagamento.GerarDemonstrativo(horasCategoria, salarioBase, horasExtras, horasDescontadas);

            var quantidadeHorasExtrasEsperado = 0;
            var valorTotalHorasExtrasEsperado = 0;
            var quantidadeHorasDescontadasEsperado = 0;
            var valorTotalHorasDescontadasEsperado = 0;
            var totalProventosEsperado = 1612.87;
            var inssAliquotaEsperado = 10;
            var inssValorEsperado = 161.28;
            var irrfAliquotaEsperado = 0;
            var irrfValorEsperado = 0;
            var totalDescontosEsperado = 161.28;
            var totalLiquidoEsperado = 1451.59;
            var fgtsAliquotaEsperado = 11;
            var fgtsValorEsperado = 177.41;

            Assert.AreEqual(demonstrativo.SalarioBase, salarioBase, MargemErro);
            Assert.AreEqual(demonstrativo.HorasExtras.QtdHoras, quantidadeHorasExtrasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasExtras.ValorTotalHoras, valorTotalHorasExtrasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasDescontadas.QtdHoras, quantidadeHorasDescontadasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasDescontadas.ValorTotalHoras, valorTotalHorasDescontadasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalProventos, totalProventosEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Inss.Aliquota, inssAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Inss.Valor, inssValorEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Irrf.Aliquota, irrfAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Irrf.Valor, irrfValorEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalDescontos, totalDescontosEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalLiquido, totalLiquidoEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Fgts.Aliquota, fgtsAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Fgts.Valor, fgtsValorEsperado, MargemErro);
        }

        [TestMethod]
        public void Validar_Demonstrativo_Industria_Salario_181234()
        {
            int horasCategoria = 200;
            double salarioBase = 1812.34;
            double horasExtras = 0;
            double horasDescontadas = 0;

            var folhaPagamento = new FolhaPagamento();
            var demonstrativo = folhaPagamento.GerarDemonstrativo(horasCategoria, salarioBase, horasExtras, horasDescontadas);

            var quantidadeHorasExtrasEsperado = 0;
            var valorTotalHorasExtrasEsperado = 0;
            var quantidadeHorasDescontadasEsperado = 0;
            var valorTotalHorasDescontadasEsperado = 0;
            var totalProventosEsperado = 1812.34;
            var inssAliquotaEsperado = 10;
            var inssValorEsperado = 181.23;
            var irrfAliquotaEsperado = 0;
            var irrfValorEsperado = 0;
            var totalDescontosEsperado = 181.23;
            var totalLiquidoEsperado = 1631.11;
            var fgtsAliquotaEsperado = 11;
            var fgtsValorEsperado = 199.36;

            Assert.AreEqual(demonstrativo.SalarioBase, salarioBase, MargemErro);
            Assert.AreEqual(demonstrativo.HorasExtras.QtdHoras, quantidadeHorasExtrasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasExtras.ValorTotalHoras, valorTotalHorasExtrasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasDescontadas.QtdHoras, quantidadeHorasDescontadasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasDescontadas.ValorTotalHoras, valorTotalHorasDescontadasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalProventos, totalProventosEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Inss.Aliquota, inssAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Inss.Valor, inssValorEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Irrf.Aliquota, irrfAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Irrf.Valor, irrfValorEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalDescontos, totalDescontosEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalLiquido, totalLiquidoEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Fgts.Aliquota, fgtsAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Fgts.Valor, fgtsValorEsperado, MargemErro);
        }

        [TestMethod]
        public void Validar_Demonstrativo_Industria_Salario_201337()
        {
            int horasCategoria = 200;
            double salarioBase = 2013.37;
            double horasExtras = 0;
            double horasDescontadas = 0;

            var folhaPagamento = new FolhaPagamento();
            var demonstrativo = folhaPagamento.GerarDemonstrativo(horasCategoria, salarioBase, horasExtras, horasDescontadas);

            var quantidadeHorasExtrasEsperado = 0;
            var valorTotalHorasExtrasEsperado = 0;
            var quantidadeHorasDescontadasEsperado = 0;
            var valorTotalHorasDescontadasEsperado = 0;
            var totalProventosEsperado = 2013.37;
            var inssAliquotaEsperado = 10;
            var inssValorEsperado = 201.33;
            var irrfAliquotaEsperado = 7.5;
            var irrfValorEsperado = 135.9;
            var totalDescontosEsperado = 337.23;
            var totalLiquidoEsperado = 1676.14;
            var fgtsAliquotaEsperado = 11;
            var fgtsValorEsperado = 221.47;

            Assert.AreEqual(demonstrativo.SalarioBase, salarioBase, MargemErro);
            Assert.AreEqual(demonstrativo.HorasExtras.QtdHoras, quantidadeHorasExtrasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasExtras.ValorTotalHoras, valorTotalHorasExtrasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasDescontadas.QtdHoras, quantidadeHorasDescontadasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasDescontadas.ValorTotalHoras, valorTotalHorasDescontadasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalProventos, totalProventosEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Inss.Aliquota, inssAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Inss.Valor, inssValorEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Irrf.Aliquota, irrfAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Irrf.Valor, irrfValorEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalDescontos, totalDescontosEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalLiquido, totalLiquidoEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Fgts.Aliquota, fgtsAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Fgts.Valor, fgtsValorEsperado, MargemErro);
        }

        [TestMethod]
        public void Validar_Demonstrativo_Industria_Salario_5000_HrE50_HrD10()
        {
            int horasCategoria = 200;
            double salarioBase = 5000;
            double horasExtras = 50;
            double horasDescontadas = 10;

            var folhaPagamento = new FolhaPagamento();
            var demonstrativo = folhaPagamento.GerarDemonstrativo(horasCategoria, salarioBase, horasExtras, horasDescontadas);

            var quantidadeHorasExtrasEsperado = 50;
            var valorTotalHorasExtrasEsperado = 1250;
            var quantidadeHorasDescontadasEsperado = 10;
            var valorTotalHorasDescontadasEsperado = 250;
            var totalProventosEsperado = 6000;
            var inssAliquotaEsperado = 10;
            var inssValorEsperado = 600;
            var irrfAliquotaEsperado = 27.5;
            var irrfValorEsperado = 1485;
            var totalDescontosEsperado = 2085;
            var totalLiquidoEsperado = 3915;
            var fgtsAliquotaEsperado = 11;
            var fgtsValorEsperado = 660;

            Assert.AreEqual(demonstrativo.SalarioBase, salarioBase, MargemErro);
            Assert.AreEqual(demonstrativo.HorasExtras.QtdHoras, quantidadeHorasExtrasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasExtras.ValorTotalHoras, valorTotalHorasExtrasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasDescontadas.QtdHoras, quantidadeHorasDescontadasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.HorasDescontadas.ValorTotalHoras, valorTotalHorasDescontadasEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalProventos, totalProventosEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Inss.Aliquota, inssAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Inss.Valor, inssValorEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Irrf.Aliquota, irrfAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Irrf.Valor, irrfValorEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalDescontos, totalDescontosEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.TotalLiquido, totalLiquidoEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Fgts.Aliquota, fgtsAliquotaEsperado, MargemErro);
            Assert.AreEqual(demonstrativo.Fgts.Valor, fgtsValorEsperado, MargemErro);
        }
    }
}
