import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConstelacaoTest {
    @Test
    public void novoGolpeNoIndex0() {
        Constelacao c = new Constelacao("Escorpião");
        Golpe g = new Golpe("Meteoro de Pégasos", 50);
        c.adicionarGolpe(g);
        assertEquals(g, c.getGolpes()[0]);
    }
    
    @Test
    public void novoGolpeNoIndex1() {
        Constelacao c = new Constelacao("Escorpião");
        Golpe g = new Golpe("Meteoro de Pégasos", 50);
        Golpe g2 = new Golpe("Cometa de Pégaso", 10);
        c.adicionarGolpe(g);
        c.adicionarGolpe(g2);
        assertEquals(g2, c.getGolpes()[1]);
    }
    
    @Test
    public void novoGolpeNoIndex2() {
        Constelacao c = new Constelacao("Escorpião");
        Golpe g = new Golpe("Meteoro de Pégasos", 50);
        Golpe g2 = new Golpe("Cometa de Pégaso", 10);
        Golpe g3 = new Golpe("Cólera do Dragão", 25);
        c.adicionarGolpe(g);
        c.adicionarGolpe(g2);
        c.adicionarGolpe(g3);
        assertEquals(g3, c.getGolpes()[2]);
    }
}