/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula1;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Lucas
 */
public class Parcelator implements IParcelator {

    public Map<String, BigDecimal> calcular(BigDecimal valorParcelar, int numeroParcelas, double taxaJuros, Date dataPrimeiroVencimento) {
        Map<String, BigDecimal> mapDeParcelas = new LinkedHashMap();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataIteracao = Calendar.getInstance();
        dataIteracao.setTime(dataPrimeiroVencimento);
        BigDecimal valorMensal = new BigDecimal(valorParcelar.doubleValue()/numeroParcelas * (1 + (taxaJuros/100)));
        for(int i = 1; i <= numeroParcelas; i++) {
            mapDeParcelas.put(dateFormat.format(dataIteracao.getTime()), valorMensal);
            dataIteracao.add(Calendar.MONTH, 1);
        }
        return mapDeParcelas;
    }
    
}
