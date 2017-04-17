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
    private int quantSentidosDespertados = 4;
    
    public Saint(String nome, Armadura armadura) throws Exception {
        this.nome = nome;
        this.armadura = armadura;
        this.quantSentidosDespertados += this.armadura.getCategoria().getValor();
        
        if(this.armadura.getCategoria() == Categoria.OURO) {
            String constelacao = this.armadura.getConstelacao();
            if(
                !constelacao.equals("Áries") 
                && !constelacao.equals("Touro")
                && !constelacao.equals("Escorpião")
                && !constelacao.equals("Peixes")
                && !constelacao.equals("Virgem")
            )
                throw new Exception("Constelação inválida.");
        }
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