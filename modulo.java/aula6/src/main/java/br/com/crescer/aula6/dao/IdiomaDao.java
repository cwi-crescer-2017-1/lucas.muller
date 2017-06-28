package br.com.crescer.aula6.dao;

import br.com.crescer.aula6.entity.Idioma;
import javax.persistence.EntityManager;

/**
 * @author carloshenrique
 */
public class IdiomaDao extends AbstractDao<Idioma, Long> {

    final EntityManager entityManager;

    public IdiomaDao(EntityManager entityManager) {
        super(Idioma.class);
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
