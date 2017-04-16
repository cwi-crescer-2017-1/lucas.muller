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
    public void perderVidaDiminuiVida() {
        Saint milo = new Saint("Milo", new Armadura("Escorpião", Categoria.OURO));
        double vida = milo.getVida();
        milo.perderVida(1.5);
        assertEquals((vida - 1.5), milo.getVida(), 0.01);
    }
}