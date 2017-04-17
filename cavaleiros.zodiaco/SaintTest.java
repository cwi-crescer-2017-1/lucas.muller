import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * Classe de testes da classe Saint
 */
public class SaintTest {    
    @Test
    public void vestirArmaduraDeixaArmaduraVestida() {
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
    public void naoVestirArmaduraDeixaArmaduraNaoVestida() {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        assertEquals(false, milo.isArmaduraVestida());
    }
    
    @Test
    public void aoCriarSaintGeneroENaoInformado() { 
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        assertEquals(Genero.NAO_INFORMADO, milo.getGenero());
    }
    
    @Test
    public void aoCriarSaintStatusEVivo() { 
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        assertEquals(Status.VIVO, milo.getStatus());
    }
    
    @Test
    public void aoCriarSaintVidaE100() { 
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        assertEquals(100.0, milo.getVida(), 0.01);
    }
    
    @Test
    public void perder10DeVida() {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        milo.perderVida(10);
        assertEquals(90, milo.getVida(), 0.01);
    }
    
    @Test
    public void perder100DeVida() {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        milo.perderVida(100);
        assertEquals(0, milo.getVida(), 0.01);
    }
    
    @Test
    public void perder1000DeVida() {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        milo.perderVida(1000);
        assertEquals(-900, milo.getVida(), 0.01);
    }
    
    @Test
    public void perderMenos1000DeVida() {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        milo.perderVida(-1000);
        assertEquals(1100, milo.getVida(), 0.01);
    }
    
    @Test
    public void setGeneroTrocaGenero() { 
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        milo.setGenero(Genero.MASCULINO);
        assertEquals(Genero.MASCULINO, milo.getGenero());
    }
    
    @Test
    public void saintDeArmaduraDeCategoriaOuroNasceCom7SentidosDespertados() {
        Saint afrodite = new Saint("Afrodite", new Armadura("Peixes", Categoria.OURO));
        assertEquals(7, afrodite.getQuantSentidosDespertados());
    }
    
    @Test
    public void saintDeArmaduraDeCategoriaPrataNasceCom6SentidosDespertados() {
        Saint marin = new Saint("Marin", new Armadura("Águia", Categoria.PRATA));
        assertEquals(6, marin.getQuantSentidosDespertados());
    }
    
    @Test
    public void saintDeArmaduraDeCategoriaBronzeNasceCom5SentidosDespertados() {
        Saint seiya = new Saint("Seiya", new Armadura("Pégaso", Categoria.BRONZE));
        assertEquals(5, seiya.getQuantSentidosDespertados());
    }
}