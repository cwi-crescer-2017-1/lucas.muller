import java.security.InvalidParameterException;
import java.util.ArrayList;

/*
 * Classe de cavaleiros(as)
 */
public abstract class Saint {
    private String nome;
    private Armadura armadura;
    private boolean armaduraVestida;
    private Genero genero = Genero.NAO_INFORMADO;
    private Status status = Status.VIVO;
    private double vida = 100;
    protected int quantSentidosDespertados = 0;
    private ArrayList<Movimento> movimentos = new ArrayList<>();
    private int ultimoMovimento = 0;
    private int ultimoGolpe = 0;
    
    public Saint(String nome, Armadura armadura) {
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
    
    public ArrayList<Golpe> getGolpes() {
        return getConstelacao().getGolpes();
    }
    
    public void aprenderGolpe(Golpe golpe) {
        getConstelacao().adicionarGolpe(golpe);
    }
    
    public Golpe getProximoGolpe() {
        if(getConstelacao().getGolpes().isEmpty())
            return null;
        Golpe g = getConstelacao().getGolpes().get(ultimoGolpe++ % getGolpes().size());
        return g;
    }
    
    public boolean hasGolpes() {
        return !getConstelacao().getGolpes().isEmpty();
    }
    
    public void adicionarMovimento(Movimento movimento) {
        this.movimentos.add(movimento);
    }
    
    public Movimento getProximoMovimento() {
        if(this.movimentos.isEmpty())
            return null;
        Movimento mov = this.movimentos.get(ultimoMovimento++ % this.movimentos.size());
        return mov;
    }
    
    public boolean hasMovimentos() {
        return !this.movimentos.isEmpty();
    }
    
    public boolean hasMovimentosDeDano() {
        return (this.movimentos.stream().filter(m -> m.movimentoTiraDano() == true).findFirst().orElse(null) != null)?true:false;
    }

    public String getNome() {
        return nome;
    }
    
    public String getCSV() {
        String s = String.format(
            "%s,%s,%s,%s,%s,%s,%s",
            this.getNome(),
            this.getVida(),
            this.armadura.getConstelacao().getNome(),
            this.armadura.getCategoria(),
            this.status.toString(),
            this.genero.toString(),
            this.armaduraVestida
        );
        return s;
    }
    
    public void adicionarMovimentoGolpear(Saint golpeado) {
        this.adicionarMovimento(new Golpear(this, golpeado));
    }
    
    public void adicionarMovimentoVestirArmadura() {
        this.adicionarMovimento(new VestirArmadura(this));
    }
    
    public boolean equals(Object object) {
        boolean equal = false;
        if(object != null && object instanceof Saint) {
            Saint s = (Saint) object;
            equal = this.nome.equals(s.nome) 
                && this.armadura.equals(s.armadura)
                && this.armaduraVestida == s.armaduraVestida
                && this.genero == s.genero
                && this.status == s.status
                && this.vida == s.vida
                && this.quantSentidosDespertados == s.quantSentidosDespertados
                && this.movimentos.equals(s.movimentos);
        }
        return equal;
    }
}