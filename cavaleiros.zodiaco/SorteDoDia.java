public class SorteDoDia {
    private Sorteador sorteador;
    
    public SorteDoDia(Sorteador sorteador) {
        this.sorteador = sorteador;
    }
    
    public boolean estouComSorte() {
        int numeroSorteado = this.sorteador.sortear();
        return (numeroSorteado % 2 == 0);
    }
}