namespace TrabalhoFinal.Infraestrutura.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AdicionarMultaPorDiaAosProdutos : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Produtos", "MultaPorDia", c => c.Double(nullable: false));
        }
        
        public override void Down()
        {
            DropColumn("dbo.Produtos", "MultaPorDia");
        }
    }
}
