import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListaSaints {
    private ArrayList<Saint> saints = new ArrayList<>();
    
    public void adicionar(Saint s) {
        this.saints.add(s);
    }
    
    public Saint get(int i) {
        return this.saints.get(i);
    }
    
    public ArrayList<Saint> todos() {
        return this.saints;
    }
    
    public void remover(Saint s) {
        this.saints.remove(s);
    }
    
    public Saint buscarPorNome(String nome) {
        return this.saints.stream().filter(s -> s.getNome().equals(nome)).findFirst().orElse(null);
    }
    
    public ArrayList<Saint> buscarPorCategoria(Categoria categoria) {
        return this.saints.stream().filter(s -> s.getArmadura().getCategoria().equals(categoria)).collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<Saint> buscarPorStatus(Status status) {
        return this.saints.stream().filter(s -> s.getStatus().equals(status)).collect(Collectors.toCollection(ArrayList::new));
    }
    
    public Saint getSaintMaiorVida() {
        if(this.saints.size() < 1)
            return null;
        
        Saint saintRetorno = this.saints.get(0);
        for(Saint s: this.saints) {
            if(s.getVida() > saintRetorno.getVida())
                saintRetorno = s;
        }
        return saintRetorno;
    }
    
    public Saint getSaintMenorVida() {
        if(this.saints.size() < 1)
            return null;
        
        Saint saintRetorno = this.saints.get(0);
        for(Saint s: this.saints) {
            if(s.getVida() < saintRetorno.getVida())
                saintRetorno = s;
        }
        return saintRetorno;
    }
    
    public void ordenar() {
        if(this.saints.size() < 1)
            return;
        
        ArrayList<Saint> listaOrdenada = new ArrayList<Saint>(this.saints);
        for (int i = 0; i < listaOrdenada.size(); i++) {
            for (int j = listaOrdenada.size() - 1; j > i; j--) {
                if (listaOrdenada.get(i).getVida() > listaOrdenada.get(j).getVida())
                    listaOrdenada.set(i, listaOrdenada.set(j, listaOrdenada.get(i)));
            }
        }
        this.saints = listaOrdenada;
    }
}