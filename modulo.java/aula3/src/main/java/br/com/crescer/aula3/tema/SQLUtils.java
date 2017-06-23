package br.com.crescer.aula3.tema;

import java.io.File;

public interface SQLUtils {

    void runFile(String filename);

    String executeQuery(String query);
    
    void importCSV(File file);
    
    File exportCSV(String query);

}
