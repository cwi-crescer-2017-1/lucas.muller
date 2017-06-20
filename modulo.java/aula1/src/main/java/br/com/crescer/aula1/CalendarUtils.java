/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula1;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Lucas
 */
public class CalendarUtils implements ICalendarUtils {

    public DiaSemana diaSemana(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch(calendar.get(Calendar.DAY_OF_WEEK)){
            case 1:
                return DiaSemana.DOMINGO;
            case 2:
                return DiaSemana.SEGUNDA_FEIRA;
            case 3:
                return DiaSemana.TERCA_FEIRA;
            case 4:
                return DiaSemana.QUARTA_FEIRA;
            case 5:
                return DiaSemana.QUINTA_FEIRA;
            case 6:
                return DiaSemana.SEXTA_FEIRA;
            case 7:
                return DiaSemana.SABADO;
            default:
                return null;
        }
    }

    public String tempoDecorrido(Date date) {
        Calendar calendarData = Calendar.getInstance();
        calendarData.setTime(date);
        Period periodoEntre = Period.between(LocalDate.now(), LocalDate.of(
                calendarData.get(Calendar.YEAR),
                calendarData.get(Calendar.MONTH),
                calendarData.get(Calendar.DAY_OF_MONTH)
        ));
        return String.format("%1s ano(s), %2s mês(es) e %3s dia(s)", 
                Math.abs(periodoEntre.getYears()),
                Math.abs(periodoEntre.getMonths()),
                Math.abs(periodoEntre.getDays())
        );
    }
    
}
