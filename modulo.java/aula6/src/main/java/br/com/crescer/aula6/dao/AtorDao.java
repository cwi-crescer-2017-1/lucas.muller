package br.com.crescer.aula6.dao;

import br.com.crescer.aula6.entity.Ator;
import javax.persistence.EntityManager;

/**
 * @author carloshenrique
 */
public class AtorDao extends AbstractDao<Ator, Long> {

    final EntityManager entityManager;

    public AtorDao(EntityManager entityManager) {
        super(Ator.class);
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
