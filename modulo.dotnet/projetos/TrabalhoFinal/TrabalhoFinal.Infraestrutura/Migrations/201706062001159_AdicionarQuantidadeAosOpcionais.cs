namespace TrabalhoFinal.Infraestrutura.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AdicionarQuantidadeAosOpcionais : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Opcionais", "Quantidade", c => c.Int());
        }
        
        public override void Down()
        {
            DropColumn("dbo.Opcionais", "Quantidade");
        }
    }
}
