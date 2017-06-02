namespace EditoraCrescer.Infraestrutura.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class RemoverRevisor : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.Livro", "IdRevisor", "dbo.Revisor");
            DropIndex("dbo.Livro", new[] { "IdRevisor" });
            AddColumn("dbo.Livro", "Revisor_Id", c => c.Int());
            CreateIndex("dbo.Livro", "Revisor_Id");
            AddForeignKey("dbo.Livro", "Revisor_Id", "dbo.Usuario", "Id");

            //Adicionado na mão
            Sql(@"IF object_id(N'[dbo].[FK_dbo.Livros_dbo.Revisores_Revisor_Id]', N'F') IS NOT NULL
                  ALTER TABLE[dbo].[Livro] DROP CONSTRAINT[FK_dbo.Livros_dbo.Revisores_Revisor_Id]");

            DropTable("dbo.Revisor");
        }
        
        public override void Down()
        {
            CreateTable(
                "dbo.Revisor",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Nome = c.String(nullable: false, maxLength: 300),
                    })
                .PrimaryKey(t => t.Id);
            
            DropForeignKey("dbo.Livro", "Revisor_Id", "dbo.Usuario");
            DropIndex("dbo.Livro", new[] { "Revisor_Id" });
            DropColumn("dbo.Livro", "Revisor_Id");
            CreateIndex("dbo.Livro", "IdRevisor");
            AddForeignKey("dbo.Livro", "IdRevisor", "dbo.Revisor", "Id", cascadeDelete: true);
        }
    }
}
