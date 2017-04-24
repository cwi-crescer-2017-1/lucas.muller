import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.security.InvalidParameterException;

/*
 * Classe de testes da classe Saint
 */
public class SaintTest {    
    @Test
    public void vestirArmaduraDeixaArmaduraVestida() throws Exception {
        // AAA
        // 1. Arrange - Montagem dos dados de teste
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        // 2. Act - Invocar a ação a ser testada
        milo.vestirArmadura();
        // 3. Assert - Verificação dos resultados do teste
        boolean result = milo.isArmaduraVestida();
        assertEquals(true, result);
    }
    
    @Test
    public void naoVestirArmaduraDeixaArmaduraNaoVestida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        assertEquals(false, milo.isArmaduraVestida());
    }
    
    @Test
    public void aoCriarSaintGeneroENaoInformado() throws Exception { 
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        assertEquals(Genero.NAO_INFORMADO, milo.getGenero());
    }
    
    @Test
    public void aoCriarSaintStatusEVivo() throws Exception { 
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        assertEquals(Status.VIVO, milo.getStatus());
    }
    
    @Test
    public void aoCriarSaintVidaE100() throws Exception { 
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        assertEquals(100.0, milo.getVida(), 0.01);
    }
    
    @Test
    public void perder10DeVida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        milo.perderVida(10);
        assertEquals(90, milo.getVida(), 0.01);
    }
    
    @Test
    public void perder100DeVida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        milo.perderVida(100);
        assertEquals(0, milo.getVida(), 0.01);
    }
    
    @Test
    public void perder1000DeVida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        milo.perderVida(1000);
        assertEquals(0, milo.getVida(), 0.01);
    }
    
    @Test(expected=InvalidParameterException.class)
    public void perderMenos1000DeVida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        milo.perderVida(-1000);
        assertEquals(1100, milo.getVida(), 0.01);
    }
    
    @Test
    public void setGeneroTrocaGenero() throws Exception { 
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        milo.setGenero(Genero.MASCULINO);
        assertEquals(Genero.MASCULINO, milo.getGenero());
    }
    
    @Test
    public void saintDeArmaduraDeCategoriaOuroNasceCom7SentidosDespertados() throws Exception {
        GoldSaint afrodite = new GoldSaint("Afrodite", "Peixes");
        assertEquals(7, afrodite.getQuantSentidosDespertados());
    }
    
    @Test
    public void saintDeArmaduraDeCategoriaPrataNasceCom6SentidosDespertados() throws Exception {
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        assertEquals(6, marin.getQuantSentidosDespertados());
    }
    
    @Test
    public void saintDeArmaduraDeCategoriaBronzeNasceCom5SentidosDespertados() throws Exception {
        BronzeSaint seiya = new BronzeSaint("Seiya", "Pégaso");
        assertEquals(5, seiya.getQuantSentidosDespertados());
    }
    
    @Test(expected=Exception.class)
    public void constelacaoInvalidaDeOuroDeveLancarErro() throws Exception {
        new GoldSaint("Lucas", "Café");
    }
    
    @Test
    public void saintMorreAoFicaCom0DeVida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        milo.perderVida(100);
        assertEquals(Status.MORTO, milo.getStatus());
    }
    
    @Test
    public void aprenderUmGolpe() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        Golpe g = new Golpe("Cólera do Dragão", 25);
        milo.aprenderGolpe(g);
        assertEquals(g, milo.getGolpes().get(0));
        assertEquals(1, milo.getGolpes().size());
    }

    @Test
    public void aprenderDoisGolpes() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        Golpe g = new Golpe("Cólera do Dragão", 25);
        Golpe g2 = new Golpe("Cometa de Pégaso", 10);
        milo.aprenderGolpe(g);
        milo.aprenderGolpe(g2);
        assertEquals(g, milo.getGolpes().get(0));
        assertEquals(g2, milo.getGolpes().get(1));
        assertEquals(2, milo.getGolpes().size());
    }

    @Test
    public void aprenderTresGolpes() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        Golpe g = new Golpe("Cólera do Dragão", 25);
        Golpe g2 = new Golpe("Cometa de Pégaso", 10);
        Golpe g3 = new Golpe("Cólera do Dragão", 25);
        milo.aprenderGolpe(g);
        milo.aprenderGolpe(g2);
        milo.aprenderGolpe(g3);
        assertEquals(g, milo.getGolpes().get(0));
        assertEquals(g2, milo.getGolpes().get(1));
        assertEquals(g3, milo.getGolpes().get(2));
        assertEquals(3, milo.getGolpes().size());
    }
    
    @Test
    public void getProximoGolpeRetornaProximoGolpe() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        Golpe g = new Golpe("Meteoro de Pégasos", 50);
        Golpe g2 = new Golpe("Cometa de Pégaso", 10);
        Golpe g3 = new Golpe("Cólera do Dragão", 25);
        milo.aprenderGolpe(g);
        milo.aprenderGolpe(g2);
        milo.aprenderGolpe(g3);
        assertEquals(g, milo.getProximoGolpe());
        assertEquals(g2, milo.getProximoGolpe());
        assertEquals(g3, milo.getProximoGolpe());
        assertEquals(g, milo.getProximoGolpe());
        assertEquals(g2, milo.getProximoGolpe());
    }
    
    @Test
    public void getProximoGolpeComListaVazia() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        assertNull(milo.getProximoGolpe());
    }
    
    @Test
    public void getProximoMovimento() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        BronzeSaint june = new BronzeSaint("June", "Camaleão");
        Movimento vestirArmadura = new VestirArmadura(milo);
        Movimento golpear = new Golpear(milo, june);
        milo.adicionarMovimento(vestirArmadura);
        milo.adicionarMovimento(golpear);
        assertEquals(vestirArmadura, milo.getProximoMovimento());
        assertEquals(golpear, milo.getProximoMovimento());
        assertEquals(vestirArmadura, milo.getProximoMovimento());
    }
    
    @Test
    public void getProximoMovimentoComListaVazia() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        assertNull(milo.getProximoMovimento());
    }
    
    @Test
    public void getCSVSemArmaduraVestida() throws Exception {
        BronzeSaint june = new BronzeSaint("June", "Camaleão");
        june.setGenero(Genero.FEMININO);
        june.perderVida(15.5);
        assertEquals("June,84.5,Camaleão,BRONZE,VIVO,FEMININO,false", june.getCSV());
    }
    
    @Test
    public void getCSVComArmaduraVestida() throws Exception {
        GoldSaint dohko = new GoldSaint("Dohko", "Áries");
        dohko.perderVida(90);
        dohko.vestirArmadura();
        assertEquals("Dohko,10.0,Áries,OURO,VIVO,NAO_INFORMADO,true", dohko.getCSV());
    }
    
    @Test
    public void adicionarMovimentoGolpearAdicionaGolpear() throws Exception {
        GoldSaint dohko = new GoldSaint("Dohko", "Áries");
        BronzeSaint june = new BronzeSaint("June", "Camaleão");
        dohko.adicionarMovimentoGolpear(june);
        assertEquals(new Golpear(dohko, june), dohko.getProximoMovimento());
    }
    
    @Test
    public void adicionarMovimentoVestirArmaduraAdicionaVestirArmadura() throws Exception {
        GoldSaint dohko = new GoldSaint("Dohko", "Áries");
        dohko.adicionarMovimentoVestirArmadura();
        assertEquals(new VestirArmadura(dohko), dohko.getProximoMovimento());
    }
}