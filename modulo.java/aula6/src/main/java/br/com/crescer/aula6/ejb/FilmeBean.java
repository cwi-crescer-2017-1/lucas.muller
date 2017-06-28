package br.com.crescer.aula6.ejb;

import br.com.crescer.aula6.dao.FilmeDao;
import br.com.crescer.aula6.entity.Filme;
import javax.ejb.Stateless;

/**
 * @author carloshenrique
 */
@Stateless
public class FilmeBean extends AbstractBean<Filme, Long, FilmeDao> {

    private FilmeDao dao;

    @Override
    public FilmeDao getDao() {
        if (dao == null) {
            dao = new FilmeDao(this.getEntityManager());
        }
        return dao;
    }

}
