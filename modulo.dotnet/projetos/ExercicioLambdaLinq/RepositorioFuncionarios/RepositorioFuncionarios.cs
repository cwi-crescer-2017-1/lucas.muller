using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Dynamic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace Repositorio
{
    public class RepositorioFuncionarios
    {
        public List<Funcionario> Funcionarios { get; private set; }
        
        public RepositorioFuncionarios()
        {
            CriarBase();
        }

        private void CriarBase()
        {
            Funcionarios = new List<Funcionario>();

            Cargo desenvolvedor1 = new Cargo("Desenvolvedor Júnior", 190);
            Cargo desenvolvedor2 = new Cargo("Desenvolvedor Pleno", 250);
            Cargo desenvolvedor3 = new Cargo("Desenvolvedor Sênior", 550.5);

            Funcionario lucasLeal = new Funcionario(1, "Marcelinho Carioca", new DateTime(1995, 01, 24));
            lucasLeal.Cargo = desenvolvedor1;
            lucasLeal.TurnoTrabalho = TurnoTrabalho.Manha;
            Funcionarios.Add(lucasLeal);

            Funcionario jeanPinzon = new Funcionario(2, "Mark Zuckerberg", new DateTime(1991, 04, 25));
            jeanPinzon.Cargo = desenvolvedor1;
            jeanPinzon.TurnoTrabalho = TurnoTrabalho.Tarde;
            Funcionarios.Add(jeanPinzon);

            Funcionario rafaelBenetti = new Funcionario(3, "Aioros de Sagitário", new DateTime(1991, 08, 15));
            rafaelBenetti.Cargo = desenvolvedor1;
            rafaelBenetti.TurnoTrabalho = TurnoTrabalho.Noite;
            Funcionarios.Add(rafaelBenetti);

            Funcionario mauricioBorges = new Funcionario(4, "Uchiha Madara", new DateTime(1996, 11, 30));
            mauricioBorges.Cargo = desenvolvedor1;
            mauricioBorges.TurnoTrabalho = TurnoTrabalho.Manha;
            Funcionarios.Add(mauricioBorges);

            Funcionario leandroAndreolli = new Funcionario(5, "Barack Obama", new DateTime(1990, 03, 07));
            leandroAndreolli.Cargo = desenvolvedor1;
            leandroAndreolli.TurnoTrabalho = TurnoTrabalho.Manha;
            Funcionarios.Add(leandroAndreolli);

            Funcionario felipeNervo = new Funcionario(6, "Whindersson  Nunes", new DateTime(1994, 01, 12));
            felipeNervo.Cargo = desenvolvedor1;
            felipeNervo.TurnoTrabalho = TurnoTrabalho.Tarde;
            Funcionarios.Add(felipeNervo);

            Funcionario lucasKauer = new Funcionario(7, "Optimus Prime", new DateTime(1997, 05, 10));
            lucasKauer.Cargo = desenvolvedor1;
            lucasKauer.TurnoTrabalho = TurnoTrabalho.Noite;
            Funcionarios.Add(lucasKauer);

            Funcionario eduardoArnold = new Funcionario(8, "Arnold Schwarzenegger", new DateTime(1989, 09, 16));
            eduardoArnold.Cargo = desenvolvedor1;
            eduardoArnold.TurnoTrabalho = TurnoTrabalho.Tarde;
            Funcionarios.Add(eduardoArnold);

            Funcionario gabrielAlboy = new Funcionario(9, "Bill Gates", new DateTime(1990, 02, 25));
            gabrielAlboy.Cargo = desenvolvedor2;
            gabrielAlboy.TurnoTrabalho = TurnoTrabalho.Manha;
            Funcionarios.Add(gabrielAlboy);

            Funcionario carlosHenrique = new Funcionario(10, "Linus Torvalds", new DateTime(1965, 12, 02));
            carlosHenrique.Cargo = desenvolvedor2;
            carlosHenrique.TurnoTrabalho = TurnoTrabalho.Tarde;
            Funcionarios.Add(carlosHenrique);

            Funcionario margareteRicardo = new Funcionario(11, "Dollynho Developer", new DateTime(1980, 10, 10));
            margareteRicardo.Cargo = desenvolvedor3;
            margareteRicardo.TurnoTrabalho = TurnoTrabalho.Manha;
            Funcionarios.Add(margareteRicardo);
        }

        public IList<Funcionario> BuscarPorCargo(Cargo cargo)
        {
            return Funcionarios.Where(funcionario => funcionario.Cargo.Equals(cargo)).ToList(); 
        }

        public IList<Funcionario> OrdenadosPorCargo()
        {
            return Funcionarios
                    .OrderBy(funcionario => funcionario.Cargo.Titulo)
                    .ThenBy(funcionario => funcionario.Nome)
                    .ToList();
        }

        public IList<Funcionario> BuscarPorNome(string nome)
        {
            return Funcionarios
                    .Where(funcionario => Regex.IsMatch(funcionario.Nome, nome, RegexOptions.IgnoreCase))
                    .ToList();
        }

        public IList<Funcionario> BuscarPorTurno(params TurnoTrabalho[] turnos)
        {
            if (turnos.Count() == 0)
                return Funcionarios;

            return Funcionarios
                    .Where(funcionario => turnos.Contains(funcionario.TurnoTrabalho))
                    .ToList();
        }

        public IList<Funcionario> FiltrarPorIdadeAproximada(int idade)
        {
            return Funcionarios
                    .Where(funcionario => (funcionario.DataNascimento.CalcularAnos()).Between(idade - 5, idade + 5, true))
                    .ToList();
        }

        public double SalarioMedio(TurnoTrabalho? turno = null)
        {
            var quantFuncionariosFiltro = 0;
            var somaSalarios = 0.0;
            if(turno == null)
            {
                Funcionarios.ForEach(funcionario => {
                    somaSalarios += funcionario.Cargo.Salario;
                    quantFuncionariosFiltro++;
                });
            }
            else
            {
                Funcionarios
                    .Where(funcionario => funcionario.TurnoTrabalho == turno)
                    .ToList()
                    .ForEach(funcionario => {
                        somaSalarios += funcionario.Cargo.Salario;
                        quantFuncionariosFiltro++;
                    });
            }
            return somaSalarios / quantFuncionariosFiltro;
        }

        public IList<Funcionario> AniversariantesDoMes()
        {
            return Funcionarios
                    .Where(funcionario => 
                        funcionario.DataNascimento.Month == DateTime.Now.Month &&
                        funcionario.DataNascimento.Year == DateTime.Now.Year
                    )
                    .ToList();
        }

        public IList<dynamic> BuscaRapida()
        {
            return Funcionarios
                    .Select(funcionario =>
                    new
                    {
                        NomeFuncionario = funcionario.Nome,
                        TituloCargo = funcionario.Cargo.Titulo
                    })
                    .ToArray();
        }

        public IList<dynamic> QuantidadeFuncionariosPorTurno()
        {
            return Funcionarios
                    .GroupBy(funcionario => funcionario.TurnoTrabalho)
                    .Select(funcionario =>
                    new
                    {
                        Turno = funcionario.First().TurnoTrabalho,
                        Quantidade = funcionario.Count()
                    })
                    .ToArray();
        }

        public dynamic FuncionarioMaisComplexo()
        {
            var filtroFuncionarios = Funcionarios
                .Where(funcionario => funcionario.Cargo.Titulo != "Desenvolvedor Júnior"
                        && funcionario.TurnoTrabalho != TurnoTrabalho.Tarde)
                .ToList();

            Funcionario funcionarioMaisComplexo = filtroFuncionarios.OrderByDescending(funcionario => funcionario.Nome.GetQuantidadeConsoantes()).First();
            var salarioFuncionarioMaisComplexo = funcionarioMaisComplexo.Cargo.Salario;
            var quantFuncionariosCargoDoFuncionarioMaisComplexo = Funcionarios.Count(funcionario => funcionario.Cargo.Equals(funcionarioMaisComplexo.Cargo));
            return new
            {
                Nome = funcionarioMaisComplexo.Nome,
                DataNascimento = funcionarioMaisComplexo.DataNascimento.ToString("dd/MM/yyy"),
                SalarioRS = string.Format(CultureInfo.GetCultureInfo("pt-BR"), "{0:C}", salarioFuncionarioMaisComplexo),
                SalarioUS = string.Format(CultureInfo.GetCultureInfo("en-US"), "{0:C}", salarioFuncionarioMaisComplexo),
                QuantidadeMesmoCargo = quantFuncionariosCargoDoFuncionarioMaisComplexo
            };
        }
    }

    #region Extensões
    public static class DateTimeExtensions
    {
        public static double CalcularAnos(this DateTime dataNascimento)
        {
            return (DateTime.Now - dataNascimento).TotalDays / 365;
        }
    }
    public static class DoubleExtensions
    {
        public static bool Between(this double num, double lower, double upper, bool inclusive = false)
        {
            return inclusive
                    ? lower <= num && num <= upper
                    : lower < num && num < upper;
        }
    }
    public static class StringExtensions
    {
        public static int GetQuantidadeConsoantes(this string palavra)
        {
            char[] chrConsonants = { 'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z',
                'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z' };
            return palavra.ToList().Count(letra => chrConsonants.Contains(letra));
        }
    }
    #endregion
}
