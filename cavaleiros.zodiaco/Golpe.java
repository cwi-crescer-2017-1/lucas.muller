public class Golpe {
    private String nome;
    private int fatorDano;
    
    public Golpe(String nome, int fatorDano) {
        this.nome = nome;
        this.fatorDano = fatorDano;
    }
    
    public static double calcularDanoTotal(Saint golpeador) {
        if(golpeador == null)
            return 0;
        
        Golpe g = golpeador.getProximoGolpe();
        Categoria cat = golpeador.getArmadura().getCategoria();
        
        if(g == null || cat == null)
            return 0;
            
        double quantVidaAPerder = g.getFatorDano();
        if(golpeador.isArmaduraVestida())
            quantVidaAPerder *= (1 + cat.getValor());
            
        return quantVidaAPerder;
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getFatorDano() {
        return fatorDano;
    }

    public boolean equals(Object object) {
        boolean equal = false;
        if(object != null && object instanceof Golpe){
            Golpe g = (Golpe)object;
            equal = this.nome.equals(g.getNome()) && this.fatorDano == g.getFatorDano();
        }
        return equal;
    }
}