package br.com.crescer.aula6.ejb;

import br.com.crescer.aula6.dao.ElencoDao;
import br.com.crescer.aula6.entity.Elenco;
import javax.ejb.Stateless;

/**
 * @author carloshenrique
 */
@Stateless
public class ElencoBean extends AbstractBean<Elenco, Long, ElencoDao> {

    private ElencoDao dao;

    @Override
    public ElencoDao getDao() {
        if (dao == null) {
            dao = new ElencoDao(this.getEntityManager());
        }
        return dao;
    }

}
