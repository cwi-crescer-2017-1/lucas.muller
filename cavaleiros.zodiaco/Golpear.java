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
            
        Golpe g = this.golpeador.getProximoGolpe();
        Categoria cat = this.golpeador.getArmadura().getCategoria();
        
        if(g == null || cat == null)
            return;
            
        double quantVidaAPerder = g.getFatorDano();
        if(this.golpeador.isArmaduraVestida())
            quantVidaAPerder *= (1 + cat.getValor());
        
        this.golpeado.perderVida(quantVidaAPerder);
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