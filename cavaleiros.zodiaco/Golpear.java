public class Golpear implements Movimento {
    private Saint golpeador, golpeado;
    private TipoMovimento tipoMovimento = TipoMovimento.OFENSIVO;
    
    public Golpear(Saint golpeador, Saint golpeado) {
        this.golpeador = golpeador;
        this.golpeado = golpeado;
    }
    
    public void executar() {
        if(this.golpeador == null || this.golpeado == null)
            return;
            
        this.golpeado.perderVida(Golpe.calcularDanoTotal(this.golpeador));
    }
    
    public TipoMovimento getTipoMovimento() {
        return this.tipoMovimento;
    }
    
    public boolean equals(Object object) {
        boolean equal = false;
        if(object != null && object instanceof Golpear) {
            Golpear g = (Golpear) object;
            equal = this.golpeador.equals(g.golpeador)
                    && this.golpeado.equals(g.golpeado);
        }
        return equal;
    }
}