import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BatalhaTest {
    @Test
    public void aoIniciarSaintDoisDeMenorCategoriaPerdeVida() {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        Saint ares = new Saint("Ares", new Armadura("Touro", Categoria.BRONZE));
        double vidaAres = ares.getVida();
        Batalha epicBattle = new Batalha(milo, ares);
        epicBattle.iniciar();
        assertEquals((vidaAres - 10), ares.getVida(), 0.01);
    }
    
    @Test
    public void aoIniciarSaintUmDeMenorCategoriaPerdeVida() {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.BRONZE));
        Saint ares = new Saint("Ares", new Armadura("Touro", Categoria.OURO));
        double vidaMilo = milo.getVida();
        Batalha epicBattle = new Batalha(milo, ares);
        epicBattle.iniciar();
        assertEquals((vidaMilo - 10), milo.getVida(), 0.01);
    }
    
    @Test
    public void aoIniciarSaintDoisDeMesmaCategoriaPerdeVida() {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        Saint ares = new Saint("Ares", new Armadura("Touro", Categoria.OURO));
        double vidaAres = ares.getVida();
        Batalha epicBattle = new Batalha(milo, ares);
        epicBattle.iniciar();
        assertEquals((vidaAres - 10), ares.getVida(), 0.01);
    }
}