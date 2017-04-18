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
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        // 2. Act - Invocar a ação a ser testada
        milo.vestirArmadura();
        // 3. Assert - Verificação dos resultados do teste
        boolean result = milo.isArmaduraVestida();
        assertEquals(true, result);
    }
    
    @Test
    public void naoVestirArmaduraDeixaArmaduraNaoVestida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        assertEquals(false, milo.isArmaduraVestida());
    }
    
    @Test
    public void aoCriarSaintGeneroENaoInformado() throws Exception { 
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        assertEquals(Genero.NAO_INFORMADO, milo.getGenero());
    }
    
    @Test
    public void aoCriarSaintStatusEVivo() throws Exception { 
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        assertEquals(Status.VIVO, milo.getStatus());
    }
    
    @Test
    public void aoCriarSaintVidaE100() throws Exception { 
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        assertEquals(100.0, milo.getVida(), 0.01);
    }
    
    @Test
    public void perder10DeVida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        milo.perderVida(10);
        assertEquals(90, milo.getVida(), 0.01);
    }
    
    @Test
    public void perder100DeVida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        milo.perderVida(100);
        assertEquals(0, milo.getVida(), 0.01);
    }
    
    @Test
    public void perder1000DeVida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        milo.perderVida(1000);
        assertEquals(-900, milo.getVida(), 0.01);
    }
    
    @Test(expected=InvalidParameterException.class)
    public void perderMenos1000DeVida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        milo.perderVida(-1000);
        assertEquals(1100, milo.getVida(), 0.01);
    }
    
    @Test
    public void setGeneroTrocaGenero() throws Exception { 
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        milo.setGenero(Genero.MASCULINO);
        assertEquals(Genero.MASCULINO, milo.getGenero());
    }
    
    @Test
    public void saintDeArmaduraDeCategoriaOuroNasceCom7SentidosDespertados() throws Exception {
        GoldSaint afrodite = new GoldSaint("Afrodite", new Armadura(new Constelacao("Peixes"), Categoria.OURO));
        assertEquals(7, afrodite.getQuantSentidosDespertados());
    }
    
    @Test
    public void saintDeArmaduraDeCategoriaPrataNasceCom6SentidosDespertados() throws Exception {
        SilverSaint marin = new SilverSaint("Marin", new Armadura(new Constelacao("Águia"), Categoria.PRATA));
        assertEquals(6, marin.getQuantSentidosDespertados());
    }
    
    @Test
    public void saintDeArmaduraDeCategoriaBronzeNasceCom5SentidosDespertados() throws Exception {
        BronzeSaint seiya = new BronzeSaint("Seiya", new Armadura(new Constelacao("Pégaso"), Categoria.BRONZE));
        assertEquals(5, seiya.getQuantSentidosDespertados());
    }
    
    @Test(expected=Exception.class)
    public void constelacaoInvalidaDeOuroDeveLancarErro() throws Exception {
        new GoldSaint("Lucas", new Armadura(new Constelacao("Café"), Categoria.OURO));
    }
    
    @Test
    public void saintMorreAoFicaCom0DeVida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        milo.perderVida(100);
        assertEquals(Status.MORTO, milo.getStatus());
    }
    
    @Test
    public void aprenderGolpeAdicionaGolpe() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        Golpe g = new Golpe("Cólera do Dragão", 25);
        milo.aprenderGolpe(g);
        assertEquals(g, milo.getGolpes()[0]);
    }
    
    @Test
    public void proximoGolpeRetornaProximoGolpe() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
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
    }
}