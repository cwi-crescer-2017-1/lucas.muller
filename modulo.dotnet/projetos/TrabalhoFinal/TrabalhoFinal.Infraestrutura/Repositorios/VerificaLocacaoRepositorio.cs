using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TrabalhoFinal.Dominio;

namespace TrabalhoFinal.Infraestrutura.Repositorios
{
    public class VerificaLocacaoRepositorio
    {
        public static bool Verifica(Locacao locacao, out List<string> mensagens)
        {
            mensagens = new List<string>();
            if (locacao.IdCliente == 0)
                mensagens.Add("Cliente inválido.");
            if (locacao.IdProduto == 0)
                mensagens.Add("Produto inválido.");
            if (locacao.DataDevolucaoPrevista <= DateTime.Now)
                mensagens.Add("Data de devolução inválida");
            if (locacao.Opcionais == null)
                locacao.Opcionais = new List<Opcional>();

            if (mensagens.Count == 0)
            {
                using (Contexto contexto = new Contexto())
                {

                    // verifica se veículo está disponível
                    Produto veiculo = contexto.Produtos.Where(x => x.Id == locacao.IdProduto).FirstOrDefault();
                    if (veiculo == null)
                        mensagens.Add("Produto não existe.");
                    else
                    {
                        int veiculosEmUso = contexto.Locacoes
                            .Count(x => x.DataDevolucaoEfetiva == null && x.IdProduto == locacao.IdProduto);
                        if (veiculo.Quantidade <= veiculosEmUso)
                            mensagens.Add("Veículo sem estoque.");
                    }

                    if (locacao.Opcionais.Count > 0)
                    {
                        // verifica se tem opcionais em estoque
                        foreach (var opcional in locacao.Opcionais)
                        {
                            Opcional op = contexto.Opcionais
                                                  .AsNoTracking()
                                                  .FirstOrDefault(x => x.Id == opcional.Id);
                            if (op == null)
                                mensagens.Add($"Opcional de ID {op.Id} não existe.");

                            // se quantidade for null é pq é ilimitado
                            if (op.Quantidade != null)
                            {
                                int quantEmUso = contexto.Locacoes
                                    .Count(x => x.DataDevolucaoEfetiva == null && x.Opcionais.Count(l=>l.Id==op.Id) > 0);
                                if (op.Quantidade <= quantEmUso)
                                    mensagens.Add($"{op.Descricao} sem estoque.");
                            }
                        }

                        // verifica regra de negócio de opcionais
                            int idMobi = Convert.ToInt32(ConfigurationManager.AppSettings["IdMobi"]);
                            int idHilux = Convert.ToInt32(ConfigurationManager.AppSettings["IdHilux"]);
                            int idKombi = Convert.ToInt32(ConfigurationManager.AppSettings["IdKombi"]);
                            int idReboque = Convert.ToInt32(ConfigurationManager.AppSettings["IdReboque"]);
                            int idRack = Convert.ToInt32(ConfigurationManager.AppSettings["IdRack"]);
                            int idCaboBateria = Convert.ToInt32(ConfigurationManager.AppSettings["IdCaboBateria"]);

                            if (locacao.IdProduto == idMobi && locacao.Opcionais.Count(x => x.Id == idReboque) > 0)
                                mensagens.Add("Mobi não pode receber um reboque.");
                            else if (locacao.IdProduto == idHilux && locacao.Opcionais.Count(x => x.Id == idRack) > 0)
                                mensagens.Add("Hilux não pode receber um rack.");
                            else if (locacao.IdProduto == idKombi && locacao.Opcionais.Count(x => x.Id == idCaboBateria) > 0)
                                mensagens.Add("Kombi não pode receber um cabo bateria.");
                    }
                }
            }

            return mensagens.Count == 0;
        }
    }
}
