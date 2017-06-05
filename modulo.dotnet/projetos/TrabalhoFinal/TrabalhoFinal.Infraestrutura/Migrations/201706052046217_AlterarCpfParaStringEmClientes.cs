namespace TrabalhoFinal.Infraestrutura.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AlterarCpfParaStringEmClientes : DbMigration
    {
        public override void Up()
        {
            AlterColumn("dbo.Clientes", "Cpf", c => c.String());
        }
        
        public override void Down()
        {
            AlterColumn("dbo.Clientes", "Cpf", c => c.Long(nullable: false));
        }
    }
}
