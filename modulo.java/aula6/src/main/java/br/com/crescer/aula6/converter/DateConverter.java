package br.com.crescer.aula6.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author carloshenrique
 */
@FacesConverter("dateConverter")
public class DateConverter implements Converter {

//    2016-12-31 
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return value != null ? SIMPLE_DATE_FORMAT.parse(value) : null;
        } catch (ParseException ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return SIMPLE_DATE_FORMAT.format((Date) value);
    }

}
