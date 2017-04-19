import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class ListaSainsTest {
    @Test
    public void buscarNomeRetornaSaintCorreto() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        SilverSaint milo2 = new SilverSaint("Milo", new Armadura(new Constelacao("Pégaso"), Categoria.PRATA));
        GoldSaint afrodite = new GoldSaint("Afrodite", new Armadura(new Constelacao("Peixes"), Categoria.OURO));
        SilverSaint marin = new SilverSaint("Marin", new Armadura(new Constelacao("Águia"), Categoria.PRATA));
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        lista.adicionar(marin);
        lista.adicionar(milo2);
        assertEquals(milo, lista.buscarPorNome("Milo"));
    }
    
    @Test
    public void buscarCategoriaOuroRetornaSaintsCorreto() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        GoldSaint afrodite = new GoldSaint("Afrodite", new Armadura(new Constelacao("Peixes"), Categoria.OURO));
        SilverSaint marin = new SilverSaint("Marin", new Armadura(new Constelacao("Águia"), Categoria.PRATA));
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        lista.adicionar(marin);
        ArrayList<Saint> subLista = lista.buscarPorCategoria(Categoria.OURO);
        assertTrue(subLista.contains(milo));
        assertTrue(subLista.contains(afrodite));
    }
    
    @Test
    public void buscarCategoriaPrataRetornaSaintsCorreto() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        GoldSaint afrodite = new GoldSaint("Afrodite", new Armadura(new Constelacao("Peixes"), Categoria.OURO));
        SilverSaint marin = new SilverSaint("Marin", new Armadura(new Constelacao("Águia"), Categoria.PRATA));
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        lista.adicionar(marin);
        ArrayList<Saint> subLista = lista.buscarPorCategoria(Categoria.PRATA);
        assertTrue(subLista.contains(marin));
    }
    
    @Test
    public void buscarStatusVivoRetornaSaintsCorreto() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        GoldSaint afrodite = new GoldSaint("Afrodite", new Armadura(new Constelacao("Peixes"), Categoria.OURO));
        SilverSaint marin = new SilverSaint("Marin", new Armadura(new Constelacao("Águia"), Categoria.PRATA));
        marin.perderVida(100);
        afrodite.perderVida(90);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        lista.adicionar(marin);
        ArrayList<Saint> subLista = lista.buscarPorStatus(Status.VIVO);
        assertTrue((subLista.contains(milo) && subLista.contains(afrodite)));
    }
    
    @Test
    public void buscarStatusMortoRetornaSaintsCorreto() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        GoldSaint afrodite = new GoldSaint("Afrodite", new Armadura(new Constelacao("Peixes"), Categoria.OURO));
        SilverSaint marin = new SilverSaint("Marin", new Armadura(new Constelacao("Águia"), Categoria.PRATA));
        marin.perderVida(100);
        afrodite.perderVida(90);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        lista.adicionar(marin);
        ArrayList<Saint> subLista = lista.buscarPorStatus(Status.MORTO);
        assertTrue(subLista.contains(marin));
    }
    
    @Test
    public void getSaintMaiorVidaFunciona() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        GoldSaint afrodite = new GoldSaint("Afrodite", new Armadura(new Constelacao("Peixes"), Categoria.OURO));
        SilverSaint marin = new SilverSaint("Marin", new Armadura(new Constelacao("Águia"), Categoria.PRATA));
        marin.perderVida(100);
        afrodite.perderVida(90);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        lista.adicionar(marin);
        assertEquals(milo, lista.getSaintMaiorVida());
    }
    
    @Test
    public void getSaintMenorVidaFunciona() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        GoldSaint afrodite = new GoldSaint("Afrodite", new Armadura(new Constelacao("Peixes"), Categoria.OURO));
        SilverSaint marin = new SilverSaint("Marin", new Armadura(new Constelacao("Águia"), Categoria.PRATA));
        marin.perderVida(100);
        afrodite.perderVida(90);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        lista.adicionar(marin);
        assertEquals(marin, lista.getSaintMenorVida());
    }
    
    @Test
    public void ordenarSaintsPorVidaFunciona() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        GoldSaint afrodite = new GoldSaint("Afrodite", new Armadura(new Constelacao("Peixes"), Categoria.OURO));
        BronzeSaint ares = new BronzeSaint("Ares", new Armadura(new Constelacao("Touro"), Categoria.BRONZE));
        SilverSaint marin = new SilverSaint("Marin", new Armadura(new Constelacao("Águia"), Categoria.PRATA));
        marin.perderVida(100);
        ares.perderVida(10);
        afrodite.perderVida(90);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        lista.adicionar(marin);
        lista.adicionar(ares);
        lista.ordenar();
        assertEquals(marin, lista.get(0));
        assertEquals(afrodite, lista.get(1));
        assertEquals(ares, lista.get(2));
        assertEquals(milo, lista.get(3));
    }
}