using Demo1.Dominio.Entidades;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Demo1.Infraestrutura.Repositorios
{
    public class ProdutoRepositorio
    {
        private const string stringConexaoBD = "Server=13.65.101.67;User Id=lucas.müller;Password=123456;Database=aluno07db;";

        public List<Produto> Listar()
        {
            var produtos = new List<Produto>();

            using (var conexao = new SqlConnection(stringConexaoBD))
            {
                conexao.Open();

                using (var comando = conexao.CreateCommand())
                {
                    comando.CommandText = @"SELECT Id, Nome, Preco, Estoque FROM Produto";
                    var dataReader = comando.ExecuteReader();

                    using (dataReader)
                    {
                        while (dataReader.Read())
                        {
                            var produto = new Produto();
                            produto.Id = (int)dataReader[0];
                            produto.Nome = (string)dataReader[1];
                            produto.Preco = (decimal)dataReader[2];
                            produto.Estoque = (int)dataReader[3];
                            produtos.Add(produto);
                        }
                    }
                }
            }

            return produtos;
        }

        public Produto Obter(int id)
        {
            using (var conexao = new SqlConnection(stringConexaoBD))
            {
                conexao.Open();

                using (var comando = conexao.CreateCommand())
                {
                    comando.CommandText = @"SELECT Id, Nome, Preco, Estoque FROM Produto WHERE Id = @id";
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
                            dataReader.Read();
                            var produto = new Produto();
                            produto.Id = (int)dataReader[0];
                            produto.Nome = (string)dataReader[1];
                            produto.Preco = (decimal)dataReader[2];
                            produto.Estoque = (int)dataReader[3];

                            return produto;
                        }
                    }
                }
            }
        }

        public void Criar(Produto produto) 
        {
            using (var conexao = new SqlConnection(stringConexaoBD))
            {
                conexao.Open();

                using (var comando = conexao.CreateCommand())
                {
                    comando.CommandText = @"INSERT INTO Produto (Nome, Preco, Estoque) 
                                            VALUES (@nome, @preco, @estoque)";
                    comando.Parameters.AddWithValue("@nome", produto.Nome);
                    comando.Parameters.AddWithValue("@preco", produto.Preco);
                    comando.Parameters.AddWithValue("@estoque", produto.Estoque);
                    comando.ExecuteNonQuery();
                }

                using (var comando = conexao.CreateCommand())
                {
                    comando.CommandText = @"SELECT @@IDENTITY";

                    produto.Id = (int)((decimal)comando.ExecuteScalar());
                }
            }
        }

        public void Alterar(Produto produto)
        {
            using (var conexao = new SqlConnection(stringConexaoBD))
            {
                conexao.Open();

                using (var comando = conexao.CreateCommand())
                {
                    comando.CommandText = @"UPDATE Produto SET 
                                            Nome = @nome,
                                            Preco = @preco,
                                            Estoque = @estoque
                                            WHERE Id = @id";
                    comando.Parameters.AddWithValue("@id", produto.Id);
                    comando.Parameters.AddWithValue("@nome", produto.Nome);
                    comando.Parameters.AddWithValue("@preco", produto.Preco);
                    comando.Parameters.AddWithValue("@estoque", produto.Estoque);
                    comando.ExecuteNonQuery();
                }
            }
        }

        public void Deletar(int id)
        {
            using (var conexao = new SqlConnection(stringConexaoBD))
            {
                conexao.Open();

                using (var comando = conexao.CreateCommand())
                {
                    comando.CommandText = @"DELETE FROM Produto WHERE Id = @id";
                    comando.Parameters.AddWithValue("@id", id);
                    comando.ExecuteNonQuery();
                }
            }
        }
    }
}
