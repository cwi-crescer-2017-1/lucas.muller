import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VestirArmaduraTest
{
    @Test
    public void vestirArmadura() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        Movimento movimento = new VestirArmadura(milo);
        movimento.executar();
        assertTrue(milo.isArmaduraVestida());
    }
    
    @Test
    public void naoVestirArmadura() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        Movimento movimento = new VestirArmadura(milo);
        assertFalse(milo.isArmaduraVestida());
    }
    
    @Test
    public void vestirArmaduraEmSaintComArmaduraVestida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        milo.vestirArmadura();
        Movimento movimento = new VestirArmadura(milo);
        movimento.executar();
        assertTrue(milo.isArmaduraVestida());
    }
    
    @Test (expected=NullPointerException.class)
    public void vestirArmaduraComSaintNull() throws Exception {
        GoldSaint milo = null;
        Movimento movimento = new VestirArmadura(milo);
        movimento.executar();
    }
}