public class Constelacao {
    private String nome;
    private Golpe[] golpes = new Golpe[3];
    
    public Constelacao(String nome) {
        this.nome = nome;
    }
    
    public void adicionarGolpe(Golpe golpe) {
        for(int i = 0; i < this.golpes.length; i++) {
            if(this.golpes[i] == null) {
                this.golpes[i] = golpe;
                break;
            }
        }
    }
    
    public Golpe[] getGolpes() {
        return this.golpes;
    }
    
    public String getNome() {
        return this.nome;
    }
}