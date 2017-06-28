package br.com.crescer.aula6.converter;

import br.com.crescer.aula6.entity.Classificacao;
import java.util.HashMap;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author carloshenrique
 */
@FacesConverter("classificacaoConverter")
public class ClassificacaoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent uIComponent, String value) {
        if (value != null) {
            return this.getInComponent(uIComponent, value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        final Classificacao classificacao = (Classificacao) value;
        if (classificacao != null) {
            this.setInComponent(component, classificacao);
            return classificacao.getId().toString();
        }
        return null;
    }

    private void setInComponent(final UIComponent uIComponent, final Classificacao classificacao) {
        Map<Long, Classificacao> classificacoes;
        classificacoes = (Map<Long, Classificacao>) uIComponent.getAttributes().get("classificacoes");
        if (classificacoes == null) {
            classificacoes = new HashMap<>();
        }
        classificacoes.put(classificacao.getId(), classificacao);
        uIComponent.getAttributes().put("classificacoes", classificacoes);
    }

    private Object getInComponent(UIComponent uIComponent, String value) throws NumberFormatException {
        Map<Long, Classificacao> classificacoes;
        classificacoes = (Map<Long, Classificacao>) uIComponent.getAttributes().get("classificacoes");
        if (classificacoes != null) {
            return classificacoes.get(Long.valueOf(value));
        }
        return null;
    }

}
