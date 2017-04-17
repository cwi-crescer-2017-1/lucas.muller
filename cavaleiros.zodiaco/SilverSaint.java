public class SilverSaint extends Saint {
    public SilverSaint(String nome, Armadura armadura) throws Exception {
        super(nome, armadura);
        if(armadura.getCategoria() != Categoria.PRATA)
            throw new Exception("Categoria da armadura inválida.");
        this.quantSentidosDespertados = 6;
    }
}