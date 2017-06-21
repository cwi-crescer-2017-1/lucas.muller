package br.com.crescer.aula1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * @author carloshenrique
 */
public class StringUtilsTest {

    private final IStringUtils stringUtils;

    public StringUtilsTest() {
        this.stringUtils = new StringUtils();
    }

    /**
     * Test of isEmpty method, of class StringUtils.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(stringUtils.isEmpty(null));
        assertTrue(stringUtils.isEmpty(""));
        assertTrue(stringUtils.isEmpty(" "));
        assertFalse(stringUtils.isEmpty("a"));
        assertFalse(stringUtils.isEmpty(" a "));
    }

    /**
     * Test of inverter method, of class StringUtils.
     */
    @Test
    public void testInverter() {
        assertEquals("solrac", stringUtils.inverter("carlos"));
    }

    /**
     * Test of contaVogais method, of class StringUtils.
     */
    @Test
    public void testContaVogais() {
        assertEquals(2, stringUtils.contaVogais("carlos"));
    }

    /**
     * Test of isPalindromo method, of class StringUtils.
     */
    @Test
    public void testIsPalindromo() {
        assertTrue(stringUtils.isPalindromo("ovo"));
        assertTrue(stringUtils.isPalindromo("Ame a Ema"));
        assertTrue(stringUtils.isPalindromo("A sogra má e amargosa"));
        assertFalse(stringUtils.isPalindromo("uva"));
        assertFalse(stringUtils.isPalindromo("Ame a Emo"));
    }

}