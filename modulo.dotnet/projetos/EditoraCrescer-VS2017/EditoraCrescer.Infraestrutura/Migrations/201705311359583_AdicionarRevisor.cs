namespace EditoraCrescer.Infraestrutura.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AdicionarRevisor : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Revisores",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Nome = c.String(nullable: false, maxLength: 300),
                    })
                .PrimaryKey(t => t.Id);
            
            AddColumn("dbo.Livros", "IdRevisor", c => c.Int(nullable: false));
            AddColumn("dbo.Livros", "DataRevisão", c => c.DateTime(nullable: false));
            AddColumn("dbo.Livros", "Revisor_Id", c => c.Int());
            AlterColumn("dbo.Autores", "Nome", c => c.String(nullable: false, maxLength: 300));
            CreateIndex("dbo.Livros", "Revisor_Id");
            AddForeignKey("dbo.Livros", "Revisor_Id", "dbo.Revisores", "Id");
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Livros", "Revisor_Id", "dbo.Revisores");
            DropIndex("dbo.Livros", new[] { "Revisor_Id" });
            AlterColumn("dbo.Autores", "Nome", c => c.String(maxLength: 300));
            DropColumn("dbo.Livros", "Revisor_Id");
            DropColumn("dbo.Livros", "DataRevisão");
            DropColumn("dbo.Livros", "IdRevisor");
            DropTable("dbo.Revisores");
        }
    }
}
