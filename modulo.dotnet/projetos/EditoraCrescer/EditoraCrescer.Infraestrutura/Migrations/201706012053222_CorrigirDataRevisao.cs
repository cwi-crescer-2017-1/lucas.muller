namespace EditoraCrescer.Infraestrutura.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class CorrigirDataRevisao : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Livro", "DataRevisao", c => c.DateTime(nullable: false));
            DropColumn("dbo.Livro", "DataRevisão");
        }
        
        public override void Down()
        {
            AddColumn("dbo.Livro", "DataRevisão", c => c.DateTime(nullable: false));
            DropColumn("dbo.Livro", "DataRevisao");
        }
    }
}
