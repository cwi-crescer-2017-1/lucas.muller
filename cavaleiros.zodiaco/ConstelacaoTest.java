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
        assertEquals(g, c.getGolpes()[0]);
        assertNull(c.getGolpes()[1]);
        assertNull(c.getGolpes()[2]);
    }
    
    @Test
    public void adicionarDoisGolpes() {
        Constelacao c = new Constelacao("Escorpião");
        Golpe g = new Golpe("Meteoro de Pégasos", 50);
        Golpe g2 = new Golpe("Cometa de Pégaso", 10);
        c.adicionarGolpe(g);
        c.adicionarGolpe(g2);
        assertEquals(g2, c.getGolpes()[1]);
        assertEquals(g, c.getGolpes()[0]);
        assertNull(c.getGolpes()[2]);
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
        assertEquals(g3, c.getGolpes()[2]);
        assertEquals(g2, c.getGolpes()[1]);
        assertEquals(g, c.getGolpes()[0]);
    }
    
    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void adicionarQuatroGolpes() {
        Constelacao c = new Constelacao("Escorpião");
        Golpe g = new Golpe("Meteoro de Pégasos", 50);
        Golpe g2 = new Golpe("Cometa de Pégaso", 10);
        Golpe g3 = new Golpe("Cólera do Dragão", 25);
        Golpe g4 = new Golpe("Meteoro do Dragão", 5);
        c.adicionarGolpe(g);
        c.adicionarGolpe(g2);
        c.adicionarGolpe(g3);
        c.adicionarGolpe(g4);
    }
}