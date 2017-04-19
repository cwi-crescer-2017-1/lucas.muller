import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConstelacaoTest {
    @Test
    public void adicionarUmGolpe() {
        Constelacao c = new Constelacao("Escorpião");
        Golpe g = new Golpe("Meteoro de Pégasos", 50);
        c.adicionarGolpe(g);
        assertEquals(g, c.getGolpes().get(0));
        assertEquals(1, c.getGolpes().size());
    }
    
    @Test
    public void adicionarDoisGolpes() {
        Constelacao c = new Constelacao("Escorpião");
        Golpe g = new Golpe("Meteoro de Pégasos", 50);
        Golpe g2 = new Golpe("Cometa de Pégaso", 10);
        c.adicionarGolpe(g);
        c.adicionarGolpe(g2);
        assertEquals(g2, c.getGolpes().get(1));
        assertEquals(g, c.getGolpes().get(0));
        assertEquals(2, c.getGolpes().size());
    }
    
    @Test
    public void adicionarTresGolpes() {
        Constelacao c = new Constelacao("Escorpião");
        Golpe g = new Golpe("Meteoro de Pégasos", 50);
        Golpe g2 = new Golpe("Cometa de Pégaso", 10);
        Golpe g3 = new Golpe("Cólera do Dragão", 25);
        c.adicionarGolpe(g);
        c.adicionarGolpe(g2);
        c.adicionarGolpe(g3);
        assertEquals(g3, c.getGolpes().get(2));
        assertEquals(g2, c.getGolpes().get(1));
        assertEquals(g, c.getGolpes().get(0));
        assertEquals(3, c.getGolpes().size());
    }
}