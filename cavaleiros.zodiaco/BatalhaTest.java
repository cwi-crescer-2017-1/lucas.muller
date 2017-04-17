import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BatalhaTest {
    @Test
    public void aoIniciarSaintDoisDeMenorCategoriaPerdeVida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura("Escorpião", Categoria.OURO));
        BronzeSaint ares = new BronzeSaint("Ares", new Armadura("Touro", Categoria.BRONZE));
        Batalha epicBattle = new Batalha(milo, ares);
        epicBattle.iniciar();
        assertEquals(100, milo.getVida(), 0.01);
        assertEquals(90, ares.getVida(), 0.01);
    }
    
    @Test
    public void aoIniciarSaintUmDeMenorCategoriaPerdeVida() throws Exception {
        BronzeSaint milo = new BronzeSaint("Milo", new Armadura("Escorpião", Categoria.BRONZE));
        GoldSaint ares = new GoldSaint("Ares", new Armadura("Touro", Categoria.OURO));
        Batalha epicBattle = new Batalha(milo, ares);
        epicBattle.iniciar();
        assertEquals(100, ares.getVida(), 0.01);
        assertEquals(90, milo.getVida(), 0.01);
    }
    
    @Test
    public void aoIniciarSaintDoisDeMesmaCategoriaPerdeVida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura("Escorpião", Categoria.OURO));
        GoldSaint ares = new GoldSaint("Ares", new Armadura("Touro", Categoria.OURO));
        Batalha epicBattle = new Batalha(milo, ares);
        epicBattle.iniciar();
        assertEquals(100, milo.getVida(), 0.01);
        assertEquals(90, ares.getVida(), 0.01);
    }
}