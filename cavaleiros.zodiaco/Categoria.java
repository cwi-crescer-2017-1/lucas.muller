/*
 * Categorias de armaduras
 */
public enum Categoria {
    OURO(3), PRATA(2), BRONZE(1);
    
    private int valor;
    
    private Categoria(int valor) {
        this.valor = valor;
    }
    
    public int getValor() {
        return this.valor;
    }
}
