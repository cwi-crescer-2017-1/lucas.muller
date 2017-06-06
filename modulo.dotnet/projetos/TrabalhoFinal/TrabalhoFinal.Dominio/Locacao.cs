using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TrabalhoFinal.Dominio
{
    public class Locacao
    {
        public int Id { get; set; }
        public int IdCliente { get; set; }
        public Cliente Cliente { get; set; }
        public int IdProduto { get; set; }
        public Produto Produto { get; set; }
        public int? IdPacote { get; set; }
        public Pacote Pacote { get; set; }
        public List<Opcional> Opcionais { get; set; }
        public DateTime DataLocacao { get; set; }
        public DateTime DataDevolucaoPrevista { get; set; }
        public DateTime? DataDevolucaoEfetiva { get; set; }
        public double PrecoFinalPrevisto { get; set; }
        public double? PrecoFinalEfetivo { get; set; }
    }
}
