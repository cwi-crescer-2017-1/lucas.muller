namespace EditoraCrescer.Infraestrutura.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class RenomeandoTabelas : DbMigration
    {
        public override void Up()
        {
            RenameTable(name: "dbo.Autores", newName: "Autor");
            RenameTable(name: "dbo.Livros", newName: "Livro");
            RenameTable(name: "dbo.Revisores", newName: "Revisor");
        }
        
        public override void Down()
        {
            RenameTable(name: "dbo.Revisor", newName: "Revisores");
            RenameTable(name: "dbo.Livro", newName: "Livros");
            RenameTable(name: "dbo.Autor", newName: "Autores");
        }
    }
}
