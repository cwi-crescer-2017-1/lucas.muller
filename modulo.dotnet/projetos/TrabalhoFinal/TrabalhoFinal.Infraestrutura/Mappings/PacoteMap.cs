using System;
using System.Collections.Generic;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TrabalhoFinal.Dominio;

namespace TrabalhoFinal.Infraestrutura.Mappings
{
    public class PacoteMap : EntityTypeConfiguration<Pacote>
    {
        public PacoteMap()
        {
            ToTable("Pacotes");
        }
    }
}
