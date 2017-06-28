package br.com.crescer.aula6.mbean;

import br.com.crescer.aula6.ejb.AtorBean;
import br.com.crescer.aula6.dao.AtorDao;
import br.com.crescer.aula6.entity.Ator;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author carloshenrique
 */
@ManagedBean
@ViewScoped
public class AtorMBean extends AbstractMBean<Ator, Long, AtorDao, AtorBean> {

    @EJB
    private AtorBean atorBean;

    @PostConstruct
    public void init() {
        this.limpar();
    }
    
    @Override
    public AtorBean getBean() {
        return atorBean;
    }

    @Override
    public void limpar() {
        this.setEntity(new Ator());
        this.setList(this.getBean().findAll());
    }

}
