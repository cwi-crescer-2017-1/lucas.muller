namespace EditoraCrescer.Infraestrutura.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class TrocarNomeFKRevisor : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.Livro", "Revisor_Id", "dbo.Usuario");
            DropIndex("dbo.Livro", new[] { "Revisor_Id" });
            DropColumn("dbo.Livro", "IdRevisor");
            RenameColumn(table: "dbo.Livro", name: "Revisor_Id", newName: "IdRevisor");
            AlterColumn("dbo.Livro", "IdRevisor", c => c.Int(nullable: false));
            CreateIndex("dbo.Livro", "IdRevisor");
            AddForeignKey("dbo.Livro", "IdRevisor", "dbo.Usuario", "Id", cascadeDelete: true);
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Livro", "IdRevisor", "dbo.Usuario");
            DropIndex("dbo.Livro", new[] { "IdRevisor" });
            AlterColumn("dbo.Livro", "IdRevisor", c => c.Int());
            RenameColumn(table: "dbo.Livro", name: "IdRevisor", newName: "Revisor_Id");
            AddColumn("dbo.Livro", "IdRevisor", c => c.Int(nullable: false));
            CreateIndex("dbo.Livro", "Revisor_Id");
            AddForeignKey("dbo.Livro", "Revisor_Id", "dbo.Usuario", "Id");
        }
    }
}
