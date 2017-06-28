package br.com.crescer.aula6.converter;

import br.com.crescer.aula6.entity.Idioma;
import java.util.HashMap;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author carloshenrique
 */
@FacesConverter("idiomaConverter")
public class IdiomaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent uIComponent, String value) {
        if (value != null) {
            return this.getInComponent(uIComponent, value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        final Idioma idioma = (Idioma) value;
        if (idioma != null) {
            this.setInComponent(component, idioma);
            return idioma.getId().toString();
        }
        return null;
    }

    private void setInComponent(final UIComponent uIComponent, final Idioma idioma) {
        Map<Long, Idioma> idiomas;
        idiomas = (Map<Long, Idioma>) uIComponent.getAttributes().get("idiomas");
        if (idiomas == null) {
            idiomas = new HashMap<>();
        }
        idiomas.put(idioma.getId(), idioma);
        uIComponent.getAttributes().put("idiomas", idiomas);
    }

    private Object getInComponent(UIComponent uIComponent, String value) throws NumberFormatException {
        Map<Long, Idioma> idiomas;
        idiomas = (Map<Long, Idioma>) uIComponent.getAttributes().get("idiomas");
        if (idiomas != null) {
            return idiomas.get(Long.valueOf(value));
        }
        return null;
    }

}
