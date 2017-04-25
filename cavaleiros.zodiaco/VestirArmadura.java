public class VestirArmadura implements Movimento {
    private Saint saint;
    private TipoMovimento tipoMovimento = TipoMovimento.DEFENSIVO;
    
    public VestirArmadura(Saint saint) {
        this.saint = saint;
    }

    public void executar() {
        this.saint.vestirArmadura();
    }
    
    public TipoMovimento getTipoMovimento() {
        return this.tipoMovimento;
    }
    
    public boolean equals(Object object) {
        boolean equal = false;
        if(object != null && object instanceof VestirArmadura) {
            VestirArmadura v = (VestirArmadura) object;
            equal = this.saint.equals(v.saint);
        }
        return equal;
    }
}