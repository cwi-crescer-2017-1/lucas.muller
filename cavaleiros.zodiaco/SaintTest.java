import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * Classe de testes da classe Saint
 */
public class SaintTest {    
    @Test
    public void vestirArmaduraDeixaArmaduraVestida() throws Exception {
        // AAA
        // 1. Arrange - Montagem dos dados de teste
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        // 2. Act - Invocar a ação a ser testada
        milo.vestirArmadura();
        // 3. Assert - Verificação dos resultados do teste
        boolean result = milo.isArmaduraVestida();
        assertEquals(true, result);
    }
    
    @Test
    public void naoVestirArmaduraDeixaArmaduraNaoVestida() throws Exception {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        assertEquals(false, milo.isArmaduraVestida());
    }
    
    @Test
    public void aoCriarSaintGeneroENaoInformado() throws Exception { 
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        assertEquals(Genero.NAO_INFORMADO, milo.getGenero());
    }
    
    @Test
    public void aoCriarSaintStatusEVivo() throws Exception { 
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        assertEquals(Status.VIVO, milo.getStatus());
    }
    
    @Test
    public void aoCriarSaintVidaE100() throws Exception { 
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        assertEquals(100.0, milo.getVida(), 0.01);
    }
    
    @Test
    public void perder10DeVida() throws Exception {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        milo.perderVida(10);
        assertEquals(90, milo.getVida(), 0.01);
    }
    
    @Test
    public void perder100DeVida() throws Exception {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        milo.perderVida(100);
        assertEquals(0, milo.getVida(), 0.01);
    }
    
    @Test
    public void perder1000DeVida() throws Exception {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        milo.perderVida(1000);
        assertEquals(-900, milo.getVida(), 0.01);
    }
    
    @Test
    public void perderMenos1000DeVida() throws Exception {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        milo.perderVida(-1000);
        assertEquals(1100, milo.getVida(), 0.01);
    }
    
    @Test
    public void setGeneroTrocaGenero() throws Exception { 
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        milo.setGenero(Genero.MASCULINO);
        assertEquals(Genero.MASCULINO, milo.getGenero());
    }
    
    @Test
    public void saintDeArmaduraDeCategoriaOuroNasceCom7SentidosDespertados() throws Exception {
        Saint afrodite = new Saint("Afrodite", new Armadura("Peixes", Categoria.OURO));
        assertEquals(7, afrodite.getQuantSentidosDespertados());
    }
    
    @Test
    public void saintDeArmaduraDeCategoriaPrataNasceCom6SentidosDespertados() throws Exception {
        Saint marin = new Saint("Marin", new Armadura("Águia", Categoria.PRATA));
        assertEquals(6, marin.getQuantSentidosDespertados());
    }
    
    @Test
    public void saintDeArmaduraDeCategoriaBronzeNasceCom5SentidosDespertados() throws Exception {
        Saint seiya = new Saint("Seiya", new Armadura("Pégaso", Categoria.BRONZE));
        assertEquals(5, seiya.getQuantSentidosDespertados());
    }
}