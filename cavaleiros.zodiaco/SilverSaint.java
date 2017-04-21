public class SilverSaint extends Saint {
    public SilverSaint(String nome, String constelacao) {
        super(nome, new Armadura(new Constelacao(constelacao), Categoria.PRATA));
        
        this.quantSentidosDespertados = 6;
    }
}