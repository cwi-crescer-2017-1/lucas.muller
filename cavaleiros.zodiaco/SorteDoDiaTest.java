import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SorteDoDiaTest
{
    @Test
    public void sorteDoDiaComDadoDeSeisRetornaTruePeloMenosUmaVez() {
        boolean retornouTrue = false;
        for(int i = 1; i <= 50; i++) {
            SorteDoDia sorte = new SorteDoDia(new DadoDeSeis());
            boolean estouComSorte = sorte.estouComSorte();
            if(estouComSorte == true) {
               retornouTrue = true;
               break;
            }
        }
        assertTrue(retornouTrue);
    }
    
    @Test
    public void sorteDoDiaComDadoDeSeisRetornaFalsePeloMenosUmaVez() {
        boolean retornouFalse = false;
        for(int i = 1; i <= 50; i++) {
            SorteDoDia sorte = new SorteDoDia(new DadoDeSeis());
            boolean estouComSorte = sorte.estouComSorte();
            if(estouComSorte == false) {
                retornouFalse = true;
                break;
            }
        }
        assertTrue(retornouFalse);
    }
    
    @Test
    public void estouSemSorteComDadoFalso() {
        SorteDoDia sorte = new SorteDoDia(new DadoFalso(1));
        assertFalse(sorte.estouComSorte());
    }
    
    @Test
    public void estouComSorteComDadoFalso() {
        SorteDoDia sorte = new SorteDoDia(new DadoFalso(4));
        assertTrue(sorte.estouComSorte());
    }
}