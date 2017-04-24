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
        
        boolean saintDoisTemCategoriaMelhor = saintDois.getArmadura().getCategoria().getValor() > saintUm.getArmadura().getCategoria().getValor();
        if(saintDoisTemCategoriaMelhor)
            golpeadorAtual = saintDois;
        
        boolean saintsTemMovimentosDeDanoEGolpes = (saintUm.hasMovimentosDeDano() || saintDois.hasMovimentosDeDano()) 
            && (saintUm.hasGolpes() || saintDois.hasGolpes());
        if(saintsTemMovimentosDeDanoEGolpes) {
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