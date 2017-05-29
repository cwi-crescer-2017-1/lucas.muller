using Demo1.Dominio.Entidades;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Demo1.Infraestrutura.Repositorios
{
    public class PedidoRepositorio
    {
        private const string stringConexaoBD = "Server=13.65.101.67;User Id=lucas.müller;Password=123456;Database=aluno07db;";

        public IEnumerable<Pedido> Listar()
        {
            using (var conexao = new SqlConnection(stringConexaoBD))
            {
                conexao.Open();

                using (var comando = conexao.CreateCommand())
                {
                    comando.CommandText = @"SELECT ped.Id, ped.NomeCliente, item.Id, item.ProdutoId, item.Quantidade
                                            FROM Pedido ped
                                            LEFT JOIN ItemPedido item on item.PedidoId = ped.Id
                                            ORDER BY ped.Id";
                    var dataReader = comando.ExecuteReader();

                    using (dataReader)
                    {
                        if (!dataReader.HasRows)
                        {
                            return null;
                        }
                        else
                        {
                            var pedidos = new List<Pedido>();
                            var idPedidoAtual = 0;
                            Pedido pedido = null;

                            while (dataReader.Read())
                            {
                                var pedidoID = (int)dataReader[0];
                                if(idPedidoAtual == 0 || pedidoID != idPedidoAtual)
                                {
                                    if (idPedidoAtual != 0) pedidos.Add(pedido);
                                    idPedidoAtual = pedidoID;

                                    pedido = new Pedido();
                                    pedido.Itens = new List<ItemPedido>();
                                    pedido.Id = (int)dataReader[0];
                                    pedido.NomeCliente = (string)dataReader[1];
                                }

                                if (dataReader[2] != DBNull.Value) {
                                    var item = new ItemPedido()
                                    {
                                        Id = (int)dataReader[2],
                                        ProdutoId = (int)dataReader[3],
                                        Quantidade = (int)dataReader[4]
                                    };
                                    pedido.Itens.Add(item);
                                }
                            }
                            pedidos.Add(pedido);

                            return pedidos;
                        }
                    }
                }
            }
        }

        public Pedido Obter(int id)
        {
            using (var conexao = new SqlConnection(stringConexaoBD))
            {
                conexao.Open();

                using (var comando = conexao.CreateCommand())
                {
                    comando.CommandText = @"SELECT ped.Id, ped.NomeCliente, item.Id, item.ProdutoId, item.Quantidade
                                            FROM Pedido ped
                                            LEFT JOIN ItemPedido item on item.PedidoId = ped.Id 
                                            WHERE ped.Id = @id";
                    comando.Parameters.AddWithValue("@id", id);
                    var dataReader = comando.ExecuteReader();

                    using (dataReader)
                    {
                        if (!dataReader.HasRows)
                        {
                            return null;
                        }
                        else
                        {
                            var pedido = new Pedido();
                            pedido.Itens = new List<ItemPedido>();

                            while (dataReader.Read())
                            {

                                if (pedido.Id == 0) pedido.Id = (int)dataReader[0];
                                if (pedido.NomeCliente == null) pedido.NomeCliente = (string)dataReader[1];

                                if (dataReader[2] == DBNull.Value)
                                    continue;
                                else
                                {
                                    var item = new ItemPedido()
                                    {
                                        Id = (int)dataReader[2],
                                        ProdutoId = (int)dataReader[3],
                                        Quantidade = (int)dataReader[4]
                                    };

                                    pedido.Itens.Add(item);
                                }
                            }

                            return pedido;
                        }
                    }
                }
            }
        }
    }
}
