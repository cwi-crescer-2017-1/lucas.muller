public class RollTheDice implements Movimento {
    private Saint golpeador;
    private Saint golpeado;
    
    public RollTheDice(Saint golpeador, Saint golpeado) {
        this.golpeador = golpeador;
        this.golpeado = golpeado;
    }
    
    public void executar() {
        if(this.golpeador == null | this.golpeado == null)
            return;
            
        if(new Porcentagem().sortear() <= 33.3) {
            // 33.3% de chance
            this.golpeado.perderVida(2 * Golpe.calcularDanoTotal(this.golpeador));
        } else {
            // fora dos 33.3%
            double vidaGolpeador = this.golpeador.getVida();
            if(vidaGolpeador < 1)
                this.golpeador.perderVida(vidaGolpeador);
            else 
                this.golpeador.perderVida(vidaGolpeador * 0.95);
        }
    }
    
    public TipoMovimento getTipoMovimento() {
        return TipoMovimento.OFENSIVO;
    }
}