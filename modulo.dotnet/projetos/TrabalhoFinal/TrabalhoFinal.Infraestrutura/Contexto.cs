
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TrabalhoFinal.Dominio;
using TrabalhoFinal.Infraestrutura.Mappings;

namespace TrabalhoFinal.Infraestrutura
{
    public class Contexto : DbContext
    {
        public Contexto() : base("name=DefaultConnection") { }

        public DbSet<Usuario> Usuarios { get; set; }
        public DbSet<Cliente> Clientes { get; set; }
        public DbSet<Produto> Produtos { get; set; }
        public DbSet<Pacote> Pacotes { get; set; }
        public DbSet<Opcional> Opcionais { get; set; }
        public DbSet<Locacao> Locacoes { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Configurations.Add(new UsuarioMap());
            modelBuilder.Configurations.Add(new ClienteMap());
            modelBuilder.Configurations.Add(new ProdutoMap());
            modelBuilder.Configurations.Add(new PacoteMap());
            modelBuilder.Configurations.Add(new OpcionalMap());
            modelBuilder.Configurations.Add(new LocacaoMap());
        }
    }
}
