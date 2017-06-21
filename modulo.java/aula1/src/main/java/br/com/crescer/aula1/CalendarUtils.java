/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula1;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

/**
 *
 * @author Lucas
 */
public class CalendarUtils implements ICalendarUtils {
    private static final Calendar CALENDAR = Calendar.getInstance();
    private static final String TEMPLATE = "%s ano(s), %s messe(s) e %s dia(s)";

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
        CALENDAR.setTime(new Date(this.getHoraZero(new Date()).getTime() - this.getHoraZero(date).getTime()));
        return String.format(TEMPLATE, (CALENDAR.get(YEAR) - 1970), CALENDAR.get(MONTH), CALENDAR.get(DAY_OF_MONTH));
    }
    
    private Date getHoraZero(Date date) {
        CALENDAR.setTime(date);
        CALENDAR.set(CALENDAR.get(YEAR), CALENDAR.get(MONTH), CALENDAR.get(DAY_OF_MONTH), 0, 0, 0);
        return CALENDAR.getTime();
    }
}
