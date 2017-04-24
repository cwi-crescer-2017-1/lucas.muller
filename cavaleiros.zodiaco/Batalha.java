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
    
    public void iniciar() {
        int categoriaSaintUm = this.saintUm.getArmadura().getCategoria().getValor();
        int categoriaSaintDois = this.saintDois.getArmadura().getCategoria().getValor();
        Saint golpeadorAtual = null;
        
        if(categoriaSaintUm >= categoriaSaintDois) {
            golpeadorAtual = this.saintUm;
            this.saintDois.perderVida(this.dano);
        } else {
            golpeadorAtual = this.saintDois;
            this.saintUm.perderVida(this.dano);
        }
        
        boolean saintsTemMovimentosDeDanoEGolpes = (saintUm.hasMovimentosDeDano() || saintDois.hasMovimentosDeDano()) 
            && (saintUm.hasGolpes() || saintDois.hasGolpes());
        if(saintsTemMovimentosDeDanoEGolpes) {
            do {
                Movimento mov = golpeadorAtual.getProximoMovimento();
                if(mov != null)
                    mov.executar();
                    
                golpeadorAtual = (golpeadorAtual == saintUm) ? saintDois : saintUm;
            } while(saintUm.getStatus() != Status.MORTO && saintDois.getStatus() != Status.MORTO);
        }
    }
}