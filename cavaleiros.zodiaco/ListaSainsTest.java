import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class ListaSainsTest {
    @Test
    public void buscarSaintExistentePorNome() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        assertEquals(milo, lista.buscarPorNome("Milo"));
    }
    
    @Test
    public void buscarSaintExistenteComRepeticaoDeNomes() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        SilverSaint milo2 = new SilverSaint("Milo", new Armadura(new Constelacao("Pégaso"), Categoria.PRATA));
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(milo2);
        assertEquals(milo, lista.buscarPorNome("Milo"));
    }
    
    @Test
    public void buscarSaintInexistentePorNome() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        assertNull(lista.buscarPorNome("Marin"));
    }
    
    @Test
    public void buscarSaintExistentePorNomeComListaVazia() {
        ListaSaints lista = new ListaSaints();
        assertNull(lista.buscarPorNome("Milo"));
    }
    
    @Test
    public void buscarPorCategoria() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        GoldSaint afrodite = new GoldSaint("Afrodite", new Armadura(new Constelacao("Peixes"), Categoria.OURO));
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        ArrayList<Saint> subLista = lista.buscarPorCategoria(Categoria.OURO);
        assertEquals(milo, subLista.get(0));
        assertEquals(afrodite, subLista.get(1));
        assertEquals(2, subLista.size());
    }
    
    @Test
    public void buscarPorCategoriaInexistente() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        ArrayList<Saint> subLista = lista.buscarPorCategoria(Categoria.PRATA);
        assertEquals(0, subLista.size());
    }
    
    @Test
    public void buscarPorCategoriaComListaVazia() {
        ListaSaints lista = new ListaSaints();
        assertEquals(0, lista.buscarPorCategoria(Categoria.OURO).size());
    }
    
    @Test
    public void buscarPorStatus() throws Exception {
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
        assertEquals(milo, subLista.get(0));
        assertEquals(afrodite, subLista.get(1));
        assertEquals(2, subLista.size());
    }
    
    @Test
    public void buscarPorStatusInexistente() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        ArrayList<Saint> subLista = lista.buscarPorStatus(Status.MORTO);
        assertEquals(0, subLista.size());
    }
    
    @Test
    public void buscarPorStatusComListaVazia() {
        ListaSaints lista = new ListaSaints();
        assertEquals(0, lista.buscarPorStatus(Status.VIVO).size());
    }
    
    @Test
    public void getSaintDeMaiorVida() throws Exception {
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
    public void getSaintDeMaiorVidaComListaVazia() throws Exception {
        assertNull(new ListaSaints().getSaintMaiorVida());
    }
    
    @Test
    public void getSaintDeMenorVida() throws Exception {
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
    public void getSaintDeMenorVidaComListaVazia() throws Exception {
        assertNull(new ListaSaints().getSaintMenorVida());
    }
    
    @Test
    public void ordenarSaintsComListaTotalmenteDesordenada() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        GoldSaint afrodite = new GoldSaint("Afrodite", new Armadura(new Constelacao("Peixes"), Categoria.OURO));
        BronzeSaint ares = new BronzeSaint("Ares", new Armadura(new Constelacao("Touro"), Categoria.BRONZE));
        SilverSaint marin = new SilverSaint("Marin", new Armadura(new Constelacao("Águia"), Categoria.PRATA));
        milo.perderVida(10);
        afrodite.perderVida(20);
        marin.perderVida(30);
        ares.perderVida(40);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        lista.adicionar(marin);
        lista.adicionar(ares);
        lista.ordenar();
        assertEquals(ares, lista.get(0));
        assertEquals(marin, lista.get(1));
        assertEquals(afrodite, lista.get(2));
        assertEquals(milo, lista.get(3));
    }
    
    @Test
    public void ordenarSaintsComListaTotalmenteOrdenada() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        GoldSaint afrodite = new GoldSaint("Afrodite", new Armadura(new Constelacao("Peixes"), Categoria.OURO));
        BronzeSaint ares = new BronzeSaint("Ares", new Armadura(new Constelacao("Touro"), Categoria.BRONZE));
        SilverSaint marin = new SilverSaint("Marin", new Armadura(new Constelacao("Águia"), Categoria.PRATA));
        milo.perderVida(10);
        afrodite.perderVida(20);
        marin.perderVida(30);
        ares.perderVida(40);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(ares);
        lista.adicionar(marin);
        lista.adicionar(afrodite);
        lista.adicionar(milo);
        lista.ordenar();
        assertEquals(ares, lista.get(0));
        assertEquals(marin, lista.get(1));
        assertEquals(afrodite, lista.get(2));
        assertEquals(milo, lista.get(3));
    }
    
    @Test
    public void ordenarSaintsComUmSaintNaLista() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.ordenar();
        assertEquals(milo, lista.get(0));
        assertFalse(lista.todos().isEmpty());
    }
    
    @Test
    public void ordenarSaintsComListaVazia() {
        ListaSaints lista = new ListaSaints();
        lista.ordenar();
        assertTrue(lista.todos().isEmpty());
    }
    
    @Test
    public void ordenarDescendenteSaintsComListaTotalmenteOrdenada() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        GoldSaint afrodite = new GoldSaint("Afrodite", new Armadura(new Constelacao("Peixes"), Categoria.OURO));
        BronzeSaint ares = new BronzeSaint("Ares", new Armadura(new Constelacao("Touro"), Categoria.BRONZE));
        SilverSaint marin = new SilverSaint("Marin", new Armadura(new Constelacao("Águia"), Categoria.PRATA));
        milo.perderVida(10);
        afrodite.perderVida(20);
        marin.perderVida(30);
        ares.perderVida(40);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        lista.adicionar(marin);
        lista.adicionar(ares);
        lista.ordenar(TipoOrdenacao.DESCENDENTE);
        assertEquals(milo, lista.get(0));
        assertEquals(afrodite, lista.get(1));
        assertEquals(marin, lista.get(2));
        assertEquals(ares, lista.get(3));
    }
    
    @Test
    public void ordenarDescendenteSaintsComListaTotalmenteDesordenada() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", new Armadura(new Constelacao("Escorpião"), Categoria.OURO));
        GoldSaint afrodite = new GoldSaint("Afrodite", new Armadura(new Constelacao("Peixes"), Categoria.OURO));
        BronzeSaint ares = new BronzeSaint("Ares", new Armadura(new Constelacao("Touro"), Categoria.BRONZE));
        SilverSaint marin = new SilverSaint("Marin", new Armadura(new Constelacao("Águia"), Categoria.PRATA));
        milo.perderVida(10);
        afrodite.perderVida(20);
        marin.perderVida(30);
        ares.perderVida(40);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(ares);
        lista.adicionar(marin);
        lista.adicionar(afrodite);
        lista.adicionar(milo);
        lista.ordenar(TipoOrdenacao.DESCENDENTE);
        assertEquals(milo, lista.get(0));
        assertEquals(afrodite, lista.get(1));
        assertEquals(marin, lista.get(2));
        assertEquals(ares, lista.get(3));
    }
    
    @Test
    public void unirListasComUmSaint() throws Exception {
        BronzeSaint ares = new BronzeSaint("Ares", new Armadura(new Constelacao("Touro"), Categoria.BRONZE));
        SilverSaint marin = new SilverSaint("Marin", new Armadura(new Constelacao("Águia"), Categoria.PRATA));
        ListaSaints lista1 = new ListaSaints();
        lista1.adicionar(ares);
        ListaSaints lista2 = new ListaSaints();
        lista2.adicionar(marin);
        ListaSaints resultado = lista1.unir(lista2);
        assertEquals(2, resultado.todos().size());
        assertEquals(ares, resultado.get(0));
        assertEquals(marin, resultado.get(1));
    }
}