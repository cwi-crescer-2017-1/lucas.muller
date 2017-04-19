import java.util.ArrayList;
import java.util.Comparator;

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
        for(Saint s: this.saints) {
            if(s.getNome().equals(nome))
                return s;
        }
        return null;
    }
    
    public ArrayList<Saint> buscarPorCategoria(Categoria categoria) {
        ArrayList<Saint> saintsSubList = new ArrayList<>();
        for(Saint s: this.saints) {
            if(s.getArmadura().getCategoria() == categoria)
                saintsSubList.add(s);
        }
        return saintsSubList;
    }
    
    public ArrayList<Saint> buscarPorStatus(Status status) {
        ArrayList<Saint> saintsSubList = new ArrayList<>();
        for(Saint s: this.saints) {
            if(s.getStatus() == status)
                saintsSubList.add(s);
        }
        return saintsSubList;
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