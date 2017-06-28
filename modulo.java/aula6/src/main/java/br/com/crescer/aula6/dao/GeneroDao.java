package br.com.crescer.aula6.dao;

import br.com.crescer.aula6.entity.Genero;
import javax.persistence.EntityManager;

/**
 * @author carloshenrique
 */
public class GeneroDao extends AbstractDao<Genero, Long> {

    final EntityManager entityManager;

    public GeneroDao(EntityManager entityManager) {
        super(Genero.class);
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
