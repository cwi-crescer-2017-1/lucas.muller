/*
 * Classe de armaduras
 */
public class Armadura {
    private Constelacao constelacao;
    private Categoria categoria;
    
    public Armadura(Constelacao constelacao, Categoria categoria) {
        this.constelacao = constelacao;
        this.categoria = categoria;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public Constelacao getConstelacao() {
        return constelacao;
    }
    
    public boolean equals(Object object) {
        boolean equal = false;
        if(object != null && object instanceof Armadura){
            Armadura a = (Armadura)object;
            equal = this.constelacao.equals(a.getConstelacao())
                && this.categoria == a.getCategoria();
        }
        return equal;
    }
}