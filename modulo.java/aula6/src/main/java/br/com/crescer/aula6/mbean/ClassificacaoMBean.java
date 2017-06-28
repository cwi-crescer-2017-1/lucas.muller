package br.com.crescer.aula6.mbean;

import br.com.crescer.aula6.ejb.ClassificacaoBean;
import br.com.crescer.aula6.dao.ClassificacaoDao;
import br.com.crescer.aula6.entity.Classificacao;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author carloshenrique
 */
@ManagedBean
@ViewScoped
public class ClassificacaoMBean extends AbstractMBean<Classificacao, Long, ClassificacaoDao, ClassificacaoBean> {

    @EJB
    private ClassificacaoBean classificacaoBean;

    @PostConstruct
    public void init() {
        this.limpar();
    }
    
    @Override
    public ClassificacaoBean getBean() {
        return classificacaoBean;
    }

    @Override
    public void limpar() {
        this.setEntity(new Classificacao());
        this.setList(this.getBean().findAll());
    }

}
