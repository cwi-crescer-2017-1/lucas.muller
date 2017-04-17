/*
 * Classe de Saints de Bronze
 */
public class BronzeSaint extends Saint {
    public BronzeSaint(String nome, Armadura armadura) throws Exception {
        super(nome, armadura);
        if(armadura.getCategoria() != Categoria.BRONZE)
            throw new Exception("Categoria da armadura inválida.");
        this.quantSentidosDespertados = 5;
    }
}