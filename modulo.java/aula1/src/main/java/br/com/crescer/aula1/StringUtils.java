/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula1;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class StringUtils implements IStringUtils {

    public boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    public String inverter(String string) {
        return new StringBuilder(string).reverse().toString();
    }

    public int contaVogais(String string) {
        return isEmpty(string) ? 0 : string.length() - normalize(string).replaceAll("[aeiouAEIOU]", "").length();
    }

    public boolean isPalindromo(String string) {
        if(isEmpty(string))
            return false;
        String stringFormatada = normalize(string).replaceAll("\\s","").toLowerCase();
        String stringFormatadaInvertida = inverter(stringFormatada);
        return stringFormatada.equalsIgnoreCase(stringFormatadaInvertida);
    }
    
    public static String normalize(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
