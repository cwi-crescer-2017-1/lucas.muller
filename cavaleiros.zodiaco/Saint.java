import java.security.InvalidParameterException;

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
    protected int quantSentidosDespertados = 0;
    private int ultimoGolpe = 0;
    
    public Saint(String nome, Armadura armadura) throws Exception {
        this.nome = nome;
        this.armadura = armadura;
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
    
    public void perderVida(double quant) throws InvalidParameterException {
        if(quant < 0)
            throw new InvalidParameterException("Quantidade de vida a ser perdida não pode ser menor que zero.");
        else if(this.status != Status.MORTO) {
            this.vida -= quant;
            if(this.vida < 1) {
                this.status = Status.MORTO;
                this.vida = 0;
            }
        }
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

	private Constelacao getConstelacao() {
		return this.armadura.getConstelacao();
	}
    
    public Golpe[] getGolpes() {
        return this.getConstelacao().getGolpes();
    }
    
    public void aprenderGolpe(Golpe golpe) {
        this.getConstelacao().adicionarGolpe(golpe);
    }
    
    public Golpe getProximoGolpe() {
        Golpe g = this.getConstelacao().getGolpes()[ultimoGolpe++];
        if(ultimoGolpe >= this.getConstelacao().getGolpes().length)
            ultimoGolpe = 0;
        return g;
    }
}