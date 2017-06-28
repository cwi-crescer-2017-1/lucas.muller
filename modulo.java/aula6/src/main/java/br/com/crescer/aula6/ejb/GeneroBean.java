package br.com.crescer.aula6.ejb;

import br.com.crescer.aula6.dao.GeneroDao;
import br.com.crescer.aula6.entity.Genero;
import javax.ejb.Stateless;

/**
 * @author carloshenrique
 */
@Stateless
public class GeneroBean extends AbstractBean<Genero, Long, GeneroDao> {

    private GeneroDao dao; 

    @Override
    public GeneroDao getDao() {
        if (dao == null) {
            dao = new GeneroDao(this.getEntityManager());
        }
        return dao;
    }

}
