package br.com.crescer.aula6.mbean;

import br.com.crescer.aula6.ejb.AtorBean;
import br.com.crescer.aula6.ejb.ElencoBean;
import br.com.crescer.aula6.dao.ElencoDao;
import br.com.crescer.aula6.entity.Ator;
import br.com.crescer.aula6.entity.Elenco;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author carloshenrique
 */
@ManagedBean
@ViewScoped
public class ElencoMBean extends AbstractMBean<Elenco, Long, ElencoDao, ElencoBean> {

    @EJB
    private ElencoBean elencoBean;

    @EJB
    private AtorBean atorBean;
    
    private List<Ator> atores;

    @PostConstruct
    public void init() {
        this.limpar();
        this.setAtores(atorBean.findAll());
    }

    @Override
    public ElencoBean getBean() {
        return elencoBean;
    }

    @Override
    public void limpar() {
        this.setEntity(new Elenco());
        this.setList(this.getBean().findAll());
    }

    public List<Ator> getAtores() {
        return atores;
    }

    public void setAtores(List<Ator> atores) {
        this.atores = atores;
    }

    
}
