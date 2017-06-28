package br.com.crescer.aula6.dao;

import br.com.crescer.aula6.entity.Elenco;
import javax.persistence.EntityManager;

/**
 * @author carloshenrique
 */
public class ElencoDao extends AbstractDao<Elenco, Long> {

    final EntityManager entityManager;

    public ElencoDao(EntityManager entityManager) {
        super(Elenco.class);
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
