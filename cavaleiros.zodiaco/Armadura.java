/*
 * Classe de armaduras
 */
public class Armadura {
    private String constelacao;
    private Categoria categoria;
    
    public Armadura(String constelacao, Categoria categoria) {
        this.constelacao = constelacao;
        this.categoria = categoria;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
}