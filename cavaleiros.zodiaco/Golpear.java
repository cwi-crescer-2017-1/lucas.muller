public class Golpear implements Movimento {
    private Saint golpeador, golpeado;
    
    public Golpear(Saint golpeador, Saint golpeado) {
        this.golpeador = golpeador;
        this.golpeado = golpeado;
    }
    
    public void executar() {
        Golpe g = this.golpeador.getProximoGolpe();
        Categoria cat = this.golpeador.getArmadura().getCategoria();
        
        if(g == null)
            return;
            
        double fatorDanoGolpe = g.getFatorDano();
        boolean isArmaduraVestida = this.golpeador.isArmaduraVestida();
        int fatorCategoria = isArmaduraVestida ? cat.getValor() : 0;
        double quantVidaAPerder = fatorDanoGolpe * (1 + fatorCategoria);
        
        this.golpeado.perderVida(quantVidaAPerder);
    }
}