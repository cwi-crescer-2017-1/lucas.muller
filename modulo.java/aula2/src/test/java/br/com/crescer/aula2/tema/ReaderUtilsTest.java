package br.com.crescer.aula2.tema;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 * @author carloshenrique
 */
public class ReaderUtilsTest {

    private static final String TARGET_PATH = "target";

    private final ReaderUtils readerUtils;

    private String filename;

    public ReaderUtilsTest() {
        this.readerUtils = new ReaderUtilsImpl();
    }

    @Before
    public void setBefore() throws IOException {
        this.filename = TARGET_PATH + "/" + new Date().getTime() + "/testRead.txt";
        final Path path = Paths.get(filename);
        Files.createDirectories(path.getParent());
        Files.createFile(path);
        Files.write(path, filename.getBytes());
    }

    /**
     * Test of read method, of class ReaderUtils.
     */
    @Test
    public void testRead() {
        final String read = readerUtils.read(filename);
        assertNotNull(read);
        assertEquals(filename, read);
    }

}