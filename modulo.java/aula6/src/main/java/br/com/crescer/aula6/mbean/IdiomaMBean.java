package br.com.crescer.aula6.mbean;

import br.com.crescer.aula6.ejb.IdiomaBean;
import br.com.crescer.aula6.dao.IdiomaDao;
import br.com.crescer.aula6.entity.Idioma;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author carloshenrique
 */
@ManagedBean
@ViewScoped
public class IdiomaMBean extends AbstractMBean<Idioma, Long, IdiomaDao, IdiomaBean> {

    @EJB
    private IdiomaBean idiomaBean;

    @PostConstruct
    public void init() {
        this.limpar();
    }

    @Override
    public IdiomaBean getBean() {
        return idiomaBean;
    }

    @Override
    public void limpar() {
        this.setEntity(new Idioma());
        this.setList(this.getBean().findAll());
    }

}
