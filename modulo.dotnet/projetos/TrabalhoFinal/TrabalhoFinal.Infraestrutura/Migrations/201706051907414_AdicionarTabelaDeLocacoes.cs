namespace TrabalhoFinal.Infraestrutura.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AdicionarTabelaDeLocacoes : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Locacoes",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        IdCliente = c.Int(nullable: false),
                        IdProduto = c.Int(nullable: false),
                        IdPacote = c.Int(nullable: false),
                        DataLocacao = c.DateTime(nullable: false),
                        DataDevolucaoPrevista = c.DateTime(nullable: false),
                        DataDevolucaoEfetiva = c.DateTime(),
                        PrecoFinalPrevisto = c.Double(nullable: false),
                        PrecoFinalEfetivo = c.Double(),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Clientes", t => t.IdCliente, cascadeDelete: true)
                .ForeignKey("dbo.Pacotes", t => t.IdPacote, cascadeDelete: true)
                .ForeignKey("dbo.Produtos", t => t.IdProduto, cascadeDelete: true)
                .Index(t => t.IdCliente)
                .Index(t => t.IdProduto)
                .Index(t => t.IdPacote);
            
            CreateTable(
                "dbo.LocacaoOpcional",
                c => new
                    {
                        IdLocacao = c.Int(nullable: false),
                        IdOpcional = c.Int(nullable: false),
                    })
                .PrimaryKey(t => new { t.IdLocacao, t.IdOpcional })
                .ForeignKey("dbo.Locacoes", t => t.IdLocacao, cascadeDelete: true)
                .ForeignKey("dbo.Opcionais", t => t.IdOpcional, cascadeDelete: true)
                .Index(t => t.IdLocacao)
                .Index(t => t.IdOpcional);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Locacoes", "IdProduto", "dbo.Produtos");
            DropForeignKey("dbo.Locacoes", "IdPacote", "dbo.Pacotes");
            DropForeignKey("dbo.LocacaoOpcional", "IdOpcional", "dbo.Opcionais");
            DropForeignKey("dbo.LocacaoOpcional", "IdLocacao", "dbo.Locacoes");
            DropForeignKey("dbo.Locacoes", "IdCliente", "dbo.Clientes");
            DropIndex("dbo.LocacaoOpcional", new[] { "IdOpcional" });
            DropIndex("dbo.LocacaoOpcional", new[] { "IdLocacao" });
            DropIndex("dbo.Locacoes", new[] { "IdPacote" });
            DropIndex("dbo.Locacoes", new[] { "IdProduto" });
            DropIndex("dbo.Locacoes", new[] { "IdCliente" });
            DropTable("dbo.LocacaoOpcional");
            DropTable("dbo.Locacoes");
        }
    }
}
