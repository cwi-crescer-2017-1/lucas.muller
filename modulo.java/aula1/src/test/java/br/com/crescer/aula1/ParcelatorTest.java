package br.com.crescer.aula1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Calendar.MONTH;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author carloshenrique
 */
public class ParcelatorTest {

    private static final Calendar CALENDAR = Calendar.getInstance();
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private final IParcelator parcelator = new Parcelator();

    public ParcelatorTest() {
    }

    /**
     * Test of calcular method, of class Parcelator.
     */
    @Test
    public void testCalcular() {
        CALENDAR.set(2017, 5, 18);
        
        final BigDecimal valor = BigDecimal.valueOf(1000l);
        final BigDecimal total = valor.multiply(BigDecimal.valueOf(10).divide(BigDecimal.valueOf(100)).add(BigDecimal.ONE)).setScale(2, RoundingMode.HALF_UP);

        final Map<String, BigDecimal> parcelas = parcelator.calcular(valor, 3, 10.0, CALENDAR.getTime());

        assertEquals(3, parcelas.size());
        
        parcelas.entrySet().forEach(e -> {
            assertEquals(DATE_FORMAT.format(CALENDAR.getTime()), e.getKey()); 
            CALENDAR.add(MONTH, 1);
        });
        assertEquals(total.doubleValue(), parcelas.entrySet().stream()
                .map(Entry::getValue)
                .collect(Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)).doubleValue(),
                0.1);
    }

}