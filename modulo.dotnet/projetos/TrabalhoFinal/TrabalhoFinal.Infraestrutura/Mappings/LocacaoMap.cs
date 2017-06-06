using System;
using System.Collections.Generic;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TrabalhoFinal.Dominio;

namespace TrabalhoFinal.Infraestrutura.Mappings
{
    public class LocacaoMap : EntityTypeConfiguration<Locacao>
    {
        public LocacaoMap()
        {
            ToTable("Locacoes");
            HasRequired(x => x.Cliente).WithMany().HasForeignKey(x => x.IdCliente);
            HasRequired(x => x.Produto).WithMany().HasForeignKey(x => x.IdProduto);
            HasRequired(x => x.Pacote).WithMany().HasForeignKey(x => x.IdPacote);
            HasMany(x => x.Opcionais).WithMany().Map(x =>
            {
                x.MapLeftKey("IdLocacao");
                x.MapRightKey("IdOpcional");
                x.ToTable("LocacaoOpcional");
            });
        }
    }
}
