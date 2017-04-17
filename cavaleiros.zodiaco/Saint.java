/*
 * Classe de cavaleiros(as)
 */
public class Saint {
    private String nome;
    private Armadura armadura;
    private boolean armaduraVestida;
    private Genero genero = Genero.NAO_INFORMADO;
    private Status status = Status.VIVO;
    private double vida = 100;
    private int quantSentidosDespertados = 5;
    //prata - 6 sentidos
    //ouro - 7 sentidos
    public Saint(String nome, Armadura armadura) {
        this.nome = nome;
        this.armadura = armadura;
        if(armadura.getCategoria() == Categoria.OURO)
            this.quantSentidosDespertados = 7;
        else if(armadura.getCategoria() == Categoria.PRATA)
            this.quantSentidosDespertados = 6;
    }
    
    public void vestirArmadura() {
        this.armaduraVestida = true;
    }
    
    public boolean isArmaduraVestida() {
        return this.armaduraVestida;
    }
    
    public Genero getGenero() {
        return genero;
    }
    
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    public void perderVida(double quant) {
        vida -= quant;
    }
    
    public Armadura getArmadura() {
        return armadura;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public double getVida() {
        return vida;
    }
    
    public int getQuantSentidosDespertados() {
        return quantSentidosDespertados;
    }
}