/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula2.tema;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Lucas
 */
public class ReaderUtilsImpl implements ReaderUtils {

    @Override
    public String read(String string) {
        if(!string.contains(".txt"))
            throw new RuntimeException("Arquivo não é um txt.");
        final File arquivo = new File(string);
        if(!arquivo.exists())
            throw new RuntimeException("Arquivo não encontrado.");
        if(arquivo.isDirectory())
            throw new RuntimeException("Arquivo inválido.");
        try (
            final Reader reader = new FileReader(arquivo);
            final BufferedReader bufferReader = new BufferedReader(reader);
        ) {
            return String.join("\n", bufferReader.lines().collect(toList()));
        } catch (Exception e) {
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }
    
}
