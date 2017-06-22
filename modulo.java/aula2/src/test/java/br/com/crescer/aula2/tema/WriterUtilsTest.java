package br.com.crescer.aula2.tema;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * @author carloshenrique
 */
public class WriterUtilsTest {

    private static final String TARGET_PATH = "target";

    private final WriterUtils writerUtils;

    private String filename;

    public WriterUtilsTest() {
        this.writerUtils = new WriterUtilsImpl();
    }

    @Before
    public void setBefore() throws IOException {
        this.filename = TARGET_PATH + "/" + new Date().getTime() + "/testWrite.txt";
        Files.createDirectories(Paths.get(filename).getParent());
    }

    /**
     * Test of write method, of class WriterUtils.
     */
    @Test
    public void testWrite() throws IOException {
        final String test = "teste de inclusão " + new Date().getTime();
        writerUtils.write(filename, test);
        assertTrue(Files.readAllLines(Paths.get(filename)).contains(test));
    }

}