/*
 * Class de batalhas entre Saints
 */
public class Batalha {
    private Saint saintUm;
    private Saint saintDois;
    
    public Batalha(Saint saintUm, Saint saintDois) {
        this.saintUm = saintUm;
        this.saintDois = saintDois;
    }
    
    public void iniciar() {
        Categoria catSaintUm = saintUm.getArmadura().getCategoria();
        Categoria catSaintDois = saintDois.getArmadura().getCategoria();
        if(catSaintUm.getValor() > catSaintDois.getValor())
            saintDois.perderVida(10);
        else if(catSaintDois.getValor() > catSaintUm.getValor())
            saintUm.perderVida(10);
        else
            saintDois.perderVida(10);
    }
    
    public Saint getSaintUm() {
        return saintUm;
    }
    
    public Saint getSaintDois() {
        return saintDois;
    }
}