package br.com.crescer.aula6.dao;

import br.com.crescer.aula6.entity.Filme;
import javax.persistence.EntityManager;

/**
 * @author carloshenrique
 */
public class FilmeDao extends AbstractDao<Filme, Long> {

    final EntityManager entityManager;

    public FilmeDao(EntityManager entityManager) {
        super(Filme.class);
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
