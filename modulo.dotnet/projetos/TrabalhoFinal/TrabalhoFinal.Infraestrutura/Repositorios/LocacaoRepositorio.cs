using System;
using System.Collections.Generic;
using System.Linq;
using System.Data.Entity;
using System.Text;
using System.Threading.Tasks;
using TrabalhoFinal.Dominio;
using System.Configuration;
using System.Data.Entity.Core.Objects;

namespace TrabalhoFinal.Infraestrutura.Repositorios
{
    public class LocacaoRepositorio : IDisposable
    {
        private Contexto contexto = new Contexto();

        public List<Locacao> Obter()
        {
            return contexto.Locacoes
                .Include(x => x.Pacote)
                .Include(x => x.Produto)
                .Include(x => x.Opcionais)
                .Include(x => x.Cliente).ToList();
        }

        public Locacao Obter(int id)
        {
            return contexto.Locacoes
                .Where(x => x.Id == id)
                .Include(x => x.Pacote)
                .Include(x => x.Produto)
                .Include(x => x.Opcionais)
                .Include(x => x.Cliente)
                .FirstOrDefault();
        }

        public List<Locacao> ObterRelatorioMensal(DateTime data)
        {
            var dataRetroativa = data.AddDays(-30);
            return contexto.Locacoes
                .Where(x => x.DataDevolucaoEfetiva != null
                            && x.DataDevolucaoEfetiva >= dataRetroativa
                            && x.DataDevolucaoEfetiva <= data)
                .Include(x => x.Pacote)
                .Include(x => x.Produto)
                .Include(x => x.Opcionais)
                .Include(x => x.Cliente)
                .ToList();
        }

        public List<Locacao> ObterRelatorioAtrasos()
        {
            return contexto.Locacoes
                .Where(x => x.DataDevolucaoEfetiva.HasValue == false
                            && x.DataDevolucaoPrevista <= DateTime.Now)
                .OrderByDescending(x => x.DataDevolucaoPrevista)
                .Include(x => x.Pacote)
                .Include(x => x.Produto)
                .Include(x => x.Opcionais)
                .Include(x => x.Cliente)
                .ToList();
        }

        public Locacao Criar(Locacao locacao)
        {
            locacao.DataLocacao = DateTime.Now;
            locacao.PrecoFinalPrevisto = CalcularPreco(locacao, locacao.DataDevolucaoPrevista);
            contexto.Locacoes.Add(locacao);
            foreach (var opcional in locacao.Opcionais)
            {
                contexto.Entry(opcional).State = EntityState.Unchanged;
            }
            contexto.SaveChanges();
            return locacao;
        }

        public bool Devolver(int id)
        {
            Locacao locacao = contexto.Locacoes.Where(x => x.Id == id).FirstOrDefault();
            if (locacao == null)
                return false;

            DateTime now = DateTime.Now.Date;
            bool atraso = now > locacao.DataDevolucaoPrevista.Date;
            locacao.DataDevolucaoEfetiva = now;
            if (!atraso)
            {
                locacao.PrecoFinalEfetivo = locacao.PrecoFinalPrevisto;
            }
            else
            {
                locacao.PrecoFinalEfetivo = CalcularPreco(locacao, now);
            }

            contexto.Entry(locacao).State = EntityState.Modified;
            contexto.SaveChanges();

            return true;
        }


        public double CalcularPreco(Locacao locacao, DateTime devolucao)
        {
            int diasLocados = (devolucao.Date - locacao.DataLocacao.Date).Days;
            Produto prod = contexto.Produtos.Where(x => x.Id == locacao.IdProduto).FirstOrDefault();

            Pacote pac = null;
            if (locacao.IdPacote != 0)
                pac = contexto.Pacotes.Where(x => x.Id == locacao.IdPacote).FirstOrDefault();

            var precoDiariaPacote = pac == null ? 0 : pac.PrecoDiaria;
            var precoOpcionais = 0.0;
            List<Opcional> opsDoBanco = new List<Opcional>();
            if (locacao.Opcionais != null)
            {
                foreach (var opcional in locacao.Opcionais)
                {
                    opsDoBanco.Add(contexto.Opcionais.AsNoTracking().Where(x => x.Id == opcional.Id).FirstOrDefault());
                }
                if (opsDoBanco.Count > 0)
                    precoOpcionais = opsDoBanco.Sum(x => x.Preco);
            }

            return (prod.PrecoDiaria  + precoDiariaPacote + precoOpcionais) * diasLocados;
        }

        public bool Remover(int id)
        {
            Locacao locacao = contexto.Locacoes.Where(x => x.Id == id).FirstOrDefault();
            if (locacao == null)
                return false;

            contexto.Locacoes.Remove(locacao);
            contexto.SaveChanges();
            return true;
        }

        public void Dispose()
        {
            ((IDisposable)contexto).Dispose();
        }
    }
}
