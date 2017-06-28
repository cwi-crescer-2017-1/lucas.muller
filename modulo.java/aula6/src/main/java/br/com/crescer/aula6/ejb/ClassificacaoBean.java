package br.com.crescer.aula6.ejb;

import br.com.crescer.aula6.dao.ClassificacaoDao;
import br.com.crescer.aula6.entity.Classificacao;
import javax.ejb.Stateless;

/**
 * @author carloshenrique
 */
@Stateless
public class ClassificacaoBean extends AbstractBean<Classificacao, Long, ClassificacaoDao> {

    private ClassificacaoDao dao;

    @Override
    public ClassificacaoDao getDao() {
        if (dao == null) {
            dao = new ClassificacaoDao(this.getEntityManager());
        }
        return dao;
    }

}
