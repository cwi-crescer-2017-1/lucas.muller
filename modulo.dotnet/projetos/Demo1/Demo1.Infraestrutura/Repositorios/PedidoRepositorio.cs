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

        public void Excluir(int id)
        {
            using (var conexao = new SqlConnection(stringConexaoBD))
            {
                conexao.Open();

                using (var comando = conexao.CreateCommand())
                {
                    comando.CommandText = @"DELETE Pedido WHERE Id = @id; DELETE ItemPedido WHERE PedidoId = @id;";
                    comando.Parameters.AddWithValue("@id", id);
                    comando.ExecuteNonQuery();
                }
            }
        }

        public void Criar(Pedido pedido)
        {
            using (var conexao = new SqlConnection(stringConexaoBD))
            {
                conexao.Open();

                foreach (ItemPedido item in pedido.Itens)
                {
                    using (var comando = conexao.CreateCommand())
                    {
                        comando.CommandText = @"SELECT Estoque FROM Produto WHERE Id = @prodId";
                        comando.Parameters.AddWithValue("@prodId", item.ProdutoId);
                        var quantProdutoEmEstoque = (int)(comando.ExecuteScalar());

                        if (quantProdutoEmEstoque < item.Quantidade)
                            throw new Exception($"Produto {item.ProdutoId} não tem estoque suficiente.");
                    }
                }

                using (var comando = conexao.CreateCommand())
                {
                    comando.CommandText = @"INSERT INTO Pedido (NomeCliente) VALUES (@nomeCliente)";
                    comando.Parameters.AddWithValue("@nomeCliente", pedido.NomeCliente);
                    comando.ExecuteNonQuery();
                }

                using (var comando = conexao.CreateCommand())
                {
                    comando.CommandText = @"SELECT @@IDENTITY";
                    pedido.Id = (int)((decimal) comando.ExecuteScalar());
                }

                
                foreach (ItemPedido item in pedido.Itens)
                {
                    using (var comando = conexao.CreateCommand())
                    {
                        comando.CommandText = @"INSERT INTO ItemPedido (PedidoId, ProdutoId, Quantidade)
                                                VALUES (@pedId, @prodId, @quant);
                                                UPDATE Produto SET Estoque = Estoque - @quant;";
                        comando.Parameters.AddWithValue("@pedId", pedido.Id);
                        comando.Parameters.AddWithValue("@prodId", item.ProdutoId);
                        comando.Parameters.AddWithValue("@quant", item.Quantidade);
                        comando.ExecuteNonQuery();
                    }

                    using (var comando = conexao.CreateCommand())
                    {
                        comando.CommandText = @"SELECT @@IDENTITY";
                        item.Id = (int)((decimal) comando.ExecuteScalar());
                    }
                }
            }
        }

        public void Alterar(Pedido pedido)
        {
            using (var conexao = new SqlConnection(stringConexaoBD))
            {
                conexao.Open();

                foreach (ItemPedido item in pedido.Itens)
                {
                    using (var comando = conexao.CreateCommand())
                    {
                        comando.CommandText = @"SELECT Estoque FROM Produto WHERE Id = @prodId";
                        comando.Parameters.AddWithValue("@prodId", item.ProdutoId);
                        var quantProdutoEmEstoque = (int)(comando.ExecuteScalar());

                        if (quantProdutoEmEstoque < item.Quantidade)
                            throw new Exception($"Produto {item.ProdutoId} não tem estoque suficiente.");
                    }
                }

                using (var comando = conexao.CreateCommand())
                {
                    comando.CommandText = @"UPDATE Pedido SET NomeCliente = @nomeCliente WHERE Id = @pedID; 
                                            DELETE ItemPedido WHERE PedidoId = @pedId;"; // deleta itens do pedido
                    comando.Parameters.AddWithValue("@nomeCliente", pedido.NomeCliente);
                    comando.Parameters.AddWithValue("@pedId", pedido.Id);
                    comando.ExecuteNonQuery();
                }

                foreach (ItemPedido item in pedido.Itens)
                {
                    using (var comando = conexao.CreateCommand())
                    {
                        comando.CommandText = @"INSERT INTO ItemPedido (PedidoId, ProdutoId, Quantidade)
                                                VALUES (@pedId, @prodId, @quant);
                                                UPDATE Produto SET Estoque = Estoque - @quant;";
                        comando.Parameters.AddWithValue("@pedId", pedido.Id);
                        comando.Parameters.AddWithValue("@prodId", item.ProdutoId);
                        comando.Parameters.AddWithValue("@quant", item.Quantidade);
                        comando.ExecuteNonQuery();
                    }

                    using (var comando = conexao.CreateCommand())
                    {
                        comando.CommandText = @"SELECT @@IDENTITY";
                        item.Id = (int)((decimal) comando.ExecuteScalar());
                    }
                }
            }
        }
    }
}
