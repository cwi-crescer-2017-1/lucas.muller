/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula2.tema;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Lucas
 */
public class FileUtilsImpl implements FileUtils {

    @Override
    public boolean mk(String string) {
        try {
            File file = new File(string);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            return file.exists() || string.matches(".*\\..{3}") ? file.createNewFile() : file.mkdir();
        } catch (IOException ex) {
            Logger.getLogger(FileUtilsImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean rm(String string) {
        final File arquivo = new File(string);
        if(!arquivo.exists())
            throw new RuntimeException("Arquivo não existe.");
        if(arquivo.isDirectory())
            throw new RuntimeException("Arquivo é inválido");
        return arquivo.delete();
    }

    @Override
    public String ls(String string) {
        final File arquivo = new File(string);
        if(!arquivo.exists())
            throw new RuntimeException("Arquivo não existe.");
        if(arquivo.isFile()) {
            return arquivo.getAbsolutePath();
        } else {
            ArrayList<String> arquivos = new ArrayList<>();
            Arrays.asList(arquivo.listFiles())
                    .stream()
                    .map(File::getName)
                    .forEach(s -> arquivos.add(s));
            return String.join("\n", arquivos);
        }
    }

    @Override
    public boolean mv(String in, String out) {
        final File arquivoIn = new File(in);
        final File arquivoOut = new File(out);
        if(arquivoIn.isDirectory() || arquivoOut.isDirectory())
            throw new RuntimeException("Arquivo é inválido");
        return arquivoIn.renameTo(arquivoOut);
    }
    
}
