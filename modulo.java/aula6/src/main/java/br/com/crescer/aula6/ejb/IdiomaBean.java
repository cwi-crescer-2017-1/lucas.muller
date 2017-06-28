package br.com.crescer.aula6.ejb;

import br.com.crescer.aula6.dao.IdiomaDao;
import br.com.crescer.aula6.entity.Idioma;
import javax.ejb.Stateless;

/**
 * @author carloshenrique
 */
@Stateless
public class IdiomaBean extends AbstractBean<Idioma, Long, IdiomaDao> {

    private IdiomaDao dao;

    @Override
    public IdiomaDao getDao() {
        if (dao == null) {
            dao = new IdiomaDao(this.getEntityManager());
        }
        return dao;
    }

}
