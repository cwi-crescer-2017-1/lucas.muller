namespace TrabalhoFinal.Infraestrutura.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AlterarNomeColunaPrecoEmPacotes : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Pacotes", "PrecoDiaria", c => c.Double(nullable: false));
            DropColumn("dbo.Pacotes", "Preco");
        }
        
        public override void Down()
        {
            AddColumn("dbo.Pacotes", "Preco", c => c.Double(nullable: false));
            DropColumn("dbo.Pacotes", "PrecoDiaria");
        }
    }
}
