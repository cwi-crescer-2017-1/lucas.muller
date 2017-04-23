import java.util.ArrayList;

public class Constelacao {
    private String nome;
    private ArrayList<Golpe> golpes = new ArrayList<Golpe>();
    
    public Constelacao(String nome) {
        this.nome = nome;
    }
    
    public void adicionarGolpe(Golpe golpe) {
		this.golpes.add(golpe);
    }
    
    public ArrayList<Golpe> getGolpes() {
        return this.golpes;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public boolean equals(Object object) {
        boolean equal = false;
        if(object != null && object instanceof Constelacao){
            Constelacao c = (Constelacao)object;
            equal = this.nome.equals(c.getNome()) && this.golpes.equals(c.getGolpes());
        }
        return equal;
    }
}