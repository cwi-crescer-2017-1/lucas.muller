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
        if(this.saints.size() > 0)
            return null;
        
        Saint saintRetorno = this.saints.get(0);
        for(Saint s: this.saints) {
            if(s.getVida() > saintRetorno.getVida())
                saintRetorno = s;
        }
        return saintRetorno;
    }
    
    public Saint getSaintMenorVida() {
        if(this.saints.size() > 0)
            return null;
        
        Saint saintRetorno = this.saints.get(0);
        for(Saint s: this.saints) {
            if(s.getVida() < saintRetorno.getVida())
                saintRetorno = s;
        }
        return saintRetorno;
    }
    
    public void ordernar() {
        this.saints.sort(new Comparator<Saint>() {
            @Override
            public int compare(Saint s1, Saint s2) {
                return Double.compare(s1.getVida(), s2.getVida());
            }
        });
    }
}