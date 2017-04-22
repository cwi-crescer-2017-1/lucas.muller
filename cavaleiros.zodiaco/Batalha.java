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
        Saint golpeadorAtual = saintUm;
        if(
            (saintUm.hasMovimentos() || saintDois.hasMovimentos()) 
            && (saintUm.hasMovimentosDeDano() || saintDois.hasMovimentosDeDano())
            && (saintUm.hasGolpes() || saintDois.hasGolpes())
        ) {
            do {
                Movimento mov = golpeadorAtual.getProximoMovimento();
                if(mov != null) {
                    mov.executar();
                    golpeadorAtual = (golpeadorAtual == saintUm) ? saintDois : saintUm;
                }
            } while(saintUm.getStatus() != Status.MORTO && saintDois.getStatus() != Status.MORTO);
        }
    }
}