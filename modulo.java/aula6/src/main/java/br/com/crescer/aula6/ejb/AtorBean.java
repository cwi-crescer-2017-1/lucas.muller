package br.com.crescer.aula6.ejb;

import br.com.crescer.aula6.dao.AtorDao;
import br.com.crescer.aula6.entity.Ator;
import javax.ejb.Stateless;

/**
 * @author carloshenrique
 */
@Stateless
public class AtorBean extends AbstractBean<Ator, Long, AtorDao> {

    private AtorDao dao;

    @Override
    public AtorDao getDao() {
        if (dao == null) {
            dao = new AtorDao(this.getEntityManager());
        }
        return dao;
    }

}
