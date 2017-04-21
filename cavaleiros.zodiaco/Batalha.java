/*
 * Classe de batalhas entre Saints
 */
public class Batalha {
    private Saint saintUm, saintDois;
    private final double dano = 10;
    
    public Batalha(Saint saintUm, Saint saintDois) {
        this.saintUm = saintUm;
        this.saintDois = saintDois;
    }
    
    /*public void iniciar() {
        Categoria catSaintUm = saintUm.getArmadura().getCategoria();
        Categoria catSaintDois = saintDois.getArmadura().getCategoria();
        if(catSaintDois.getValor() > catSaintUm.getValor())
            saintUm.perderVida(dano);
        else saintDois.perderVida(dano);
    }*/
    
    public void iniciar() {
        Saint golpeadorAtual = saintUm;
        do {
            golpeadorAtual.getProximoMovimento().executar();
            golpeadorAtual = (golpeadorAtual == saintUm) ? saintDois : saintUm;
        } while(saintUm.getStatus() != Status.MORTO && saintDois.getStatus() != Status.MORTO);
    }
}