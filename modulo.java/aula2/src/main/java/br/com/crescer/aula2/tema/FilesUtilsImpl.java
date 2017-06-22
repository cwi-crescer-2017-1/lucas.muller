/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula2.tema;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class FilesUtilsImpl implements FileUtils {

    @Override
    public boolean mk(String string) {
        try {
            return new File(string).createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(FilesUtilsImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean rm(String string) {
        final File arquivo = new File(string);
        if(arquivo.isDirectory()) {
            System.out.println("Arquivo é inválido");
            return false;
        }
        return arquivo.delete();
    }

    @Override
    public String ls(String string) {
        final File arquivo = new File(string);
        if(arquivo.isFile())
            return arquivo.getAbsolutePath();
        // se é diretório:
        StringBuilder arquivos = new StringBuilder();
        Arrays.asList(arquivo.listFiles())
                .stream()
                .map(File::getName)
                .forEach(nome -> arquivos.append(nome).append("\n"));
        return arquivos.toString();
    }

    @Override
    public boolean mv(String in, String out) {
        final File arquivoIn = new File(in);
        final File arquivoOut = new File(out);
        if(arquivoIn.isDirectory() || arquivoOut.isDirectory()) {
            System.out.println("Arquivo é inválido");
            return false;
        }
        return arquivoIn.renameTo(arquivoOut);
    }
    
}
