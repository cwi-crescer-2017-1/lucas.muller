package br.com.crescer.aula6.dao;

import br.com.crescer.aula6.entity.Classificacao;
import javax.persistence.EntityManager;

/**
 * @author carloshenrique
 */
public class ClassificacaoDao extends AbstractDao<Classificacao, Long>{

    final EntityManager entityManager;

    public ClassificacaoDao(EntityManager entityManager) {
        super(Classificacao.class);
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
}
