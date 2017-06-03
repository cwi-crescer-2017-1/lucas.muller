namespace EditoraCrescer.Infraestrutura.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class SetarCamposOpcionaisEmLivros : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.Livro", "IdRevisor", "dbo.Usuario");
            DropIndex("dbo.Livro", new[] { "IdRevisor" });
            AlterColumn("dbo.Livro", "DataPublicacao", c => c.DateTime());
            AlterColumn("dbo.Livro", "IdRevisor", c => c.Int());
            AlterColumn("dbo.Livro", "DataRevisao", c => c.DateTime());
            CreateIndex("dbo.Livro", "IdRevisor");
            AddForeignKey("dbo.Livro", "IdRevisor", "dbo.Usuario", "Id");
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Livro", "IdRevisor", "dbo.Usuario");
            DropIndex("dbo.Livro", new[] { "IdRevisor" });
            AlterColumn("dbo.Livro", "DataRevisao", c => c.DateTime(nullable: false));
            AlterColumn("dbo.Livro", "IdRevisor", c => c.Int(nullable: false));
            AlterColumn("dbo.Livro", "DataPublicacao", c => c.DateTime(nullable: false));
            CreateIndex("dbo.Livro", "IdRevisor");
            AddForeignKey("dbo.Livro", "IdRevisor", "dbo.Usuario", "Id", cascadeDelete: true);
        }
    }
}
