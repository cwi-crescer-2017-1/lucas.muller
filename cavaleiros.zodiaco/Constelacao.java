public class Constelacao {
    private String nome;
    private Golpe[] golpes = new Golpe[3];
	private int ultimoIndexGolpe = 0;
    
    public Constelacao(String nome) {
        this.nome = nome;
    }
    
    public void adicionarGolpe(Golpe golpe) {
		this.golpes[ultimoIndexGolpe++] = golpe;
    }
    
    public Golpe[] getGolpes() {
        return this.golpes;
    }
    
    public String getNome() {
        return this.nome;
    }
}