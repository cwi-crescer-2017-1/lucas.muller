import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GolpearTest
{
    @Test
    public void golpearComArmaduraVestidaECategoriaOuro() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        BronzeSaint june = new BronzeSaint("June", "Camaleão");
        Golpe meteoro = new Golpe("Meteoro de Pégasos", 10);
        Golpear golpear = new Golpear(milo, june);
        
        milo.aprenderGolpe(meteoro);
        milo.vestirArmadura();
        golpear.executar();
        
        assertEquals(60, june.getVida(), 0.01);
    }
    
    @Test
    public void golpearComArmaduraNaoVestidaECategoriaOuro() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        BronzeSaint june = new BronzeSaint("June", "Camaleão");
        Golpe meteoro = new Golpe("Meteoro de Pégasos", 10);
        Golpear golpear = new Golpear(milo, june);
        
        milo.aprenderGolpe(meteoro);
        golpear.executar();
        
        assertEquals(90, june.getVida(), 0.01);
    }
    
    @Test
    public void golpearComArmaduraVestidaECategoriaPrata() throws Exception {
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint june = new BronzeSaint("June", "Camaleão");
        Golpe meteoro = new Golpe("Meteoro de Pégasos", 10);
        Golpear golpear = new Golpear(marin, june);
        
        marin.aprenderGolpe(meteoro);
        marin.vestirArmadura();
        golpear.executar();
        
        assertEquals(70, june.getVida(), 0.01);
    }
    
    @Test
    public void golpearComArmaduraNaoVestidaECategoriaPrata() throws Exception {
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint june = new BronzeSaint("June", "Camaleão");
        Golpe meteoro = new Golpe("Meteoro de Pégasos", 10);
        Golpear golpear = new Golpear(marin, june);
        
        marin.aprenderGolpe(meteoro);
        golpear.executar();
        
        assertEquals(90, june.getVida(), 0.01);
    }
    
    @Test
    public void golpearComArmaduraVestidaECategoriaBronze() throws Exception {
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint june = new BronzeSaint("June", "Camaleão");
        Golpe meteoro = new Golpe("Meteoro de Pégasos", 10);
        Golpear golpear = new Golpear(june, marin);
        
        june.aprenderGolpe(meteoro);
        june.vestirArmadura();
        golpear.executar();
        
        assertEquals(80, marin.getVida(), 0.01);
    }
    
    @Test
    public void golpearComArmaduraNaoVestidaECategoriaBronze() throws Exception {
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint june = new BronzeSaint("June", "Camaleão");
        Golpe meteoro = new Golpe("Meteoro de Pégasos", 10);
        Golpear golpear = new Golpear(june, marin);
        
        june.aprenderGolpe(meteoro);
        golpear.executar();
        
        assertEquals(90, marin.getVida(), 0.01);
    }
    
    @Test
    public void golpearComSaintSemGolpesAprendidos() {
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint june = new BronzeSaint("June", "Camaleão");
        Golpear golpear = new Golpear(june, marin);
        
        assertEquals(100, marin.getVida(), 0.01);
    }
}
