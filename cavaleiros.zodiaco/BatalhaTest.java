import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BatalhaTest {
    @Test
    public void aoIniciarSaintDoisDeMenorCategoriaPerdeVida() throws Exception {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        Saint ares = new Saint("Ares", new Armadura("Touro", Categoria.BRONZE));
        Batalha epicBattle = new Batalha(milo, ares);
        epicBattle.iniciar();
        assertEquals(100, milo.getVida(), 0.01);
        assertEquals(90, ares.getVida(), 0.01);
    }
    
    @Test
    public void aoIniciarSaintUmDeMenorCategoriaPerdeVida() throws Exception {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.BRONZE));
        Saint ares = new Saint("Ares", new Armadura("Touro", Categoria.OURO));
        Batalha epicBattle = new Batalha(milo, ares);
        epicBattle.iniciar();
        assertEquals(100, ares.getVida(), 0.01);
        assertEquals(90, milo.getVida(), 0.01);
    }
    
    @Test
    public void aoIniciarSaintDoisDeMesmaCategoriaPerdeVida() throws Exception {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        Saint ares = new Saint("Ares", new Armadura("Touro", Categoria.OURO));
        Batalha epicBattle = new Batalha(milo, ares);
        epicBattle.iniciar();
        assertEquals(100, milo.getVida(), 0.01);
        assertEquals(90, ares.getVida(), 0.01);
    }
}