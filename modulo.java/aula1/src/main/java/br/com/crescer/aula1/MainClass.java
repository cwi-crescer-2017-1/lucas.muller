/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula1;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas.muller
 */
public class MainClass {
    public static void main(String[] args) {
        // System.out.println("Hello Lucas!");
        
        // stringBuilderExerc01();
        
        // exercLeData();
        
        // testaParcelador();
        
        // System.out.println(new StringUtils().isPalindromo("A sogra má e amargosa"));
    }
    
    /* 
        https://goo.gl/FRoPYr

        Usando o enum de Estados, utilize o StringBuffer para concatenar 
        os nomes do estados separados por virgula, e exiba-os na console. 
        Se conseguir ordenado melhor.
    */
    private static void stringBuilderExerc01() {
        StringBuilder estadosConcatenados = new StringBuilder();
        Estados[] estados = Estados.values();
        Arrays.sort(estados, Comparator.comparing(e -> 
            // remover acentos na comparação
            Normalizer.normalize(e.getNome(), Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
        ));
        for(Estados estado: estados)
            estadosConcatenados.append(estado.getNome()).append(", ");
        
        estadosConcatenados.deleteCharAt(estadosConcatenados.length() - 2);
        System.out.println(estadosConcatenados.toString());
    }
    
    private static void exercLeData() {
        try(final Scanner scan = new Scanner(System.in)) {
            final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            // obtem a data inicial
            System.out.print("Digite a data no formato dd/MM/yyyy: ");
            String dataNaoFormatada = scan.nextLine();
            
            // obtem a quantidade de dias a serem adicionados
            System.out.print("Digite a quantidade de dias a serem adicionados: ");
            int diasParaAdd = scan.nextInt();
            
            // adiciona os dias a data inicial
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(dataNaoFormatada));
            calendar.add(Calendar.DAY_OF_YEAR, diasParaAdd);
            
            // imprime a data final
            System.out.println("A data final é: " + dateFormat.format(calendar.getTime()));
        }
        catch(Exception e) {
            System.out.println("Houve um erro: " + e.getMessage());
        }
    }

    private static void testaParcelador() {
        try {
            Parcelador parcelador = new Parcelador();
            Map<String, BigDecimal> parcelas = parcelador.calcular(new BigDecimal(1000), 10, 10, new SimpleDateFormat("dd/MM/yyyy").parse("30/06/2016"));
            parcelas.forEach((String data, BigDecimal valor)->{
                System.out.println(data + " - " + valor.doubleValue());
            });
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    
    private static void testaTempoDecorrido() {
        try {
            Date dataParaComparar = new SimpleDateFormat("dd/MM/yyyy").parse("27/10/1999");
            System.out.println(new CalendarUtils().tempoDecorrido(dataParaComparar));
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    
    private static void testaDiaSemana() {
        try {
            System.out.println(new CalendarUtils().diaSemana(new Date()));
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
