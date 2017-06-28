package br.com.crescer.aula6.ejb;

import br.com.crescer.aula6.dao.AbstractDao;
import br.com.crescer.aula6.interfaces.CrudDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author carloshenrique
 * @param <T>
 * @param <ID>
 * @param <DAO>
 */
public abstract class AbstractBean<T, ID, DAO extends AbstractDao<T, ID>> implements CrudDao<T, ID> {

    @PersistenceContext(unitName = "crescer")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public abstract DAO getDao();

    @Override
    public void insert(T t) {
        this.getDao().insert(t);
    }

    @Override
    public void delete(T t) {
      this.getDao().delete(t);
    }

    @Override
    public List<T> findAll() {
        return this.getDao().findAll();
    }

    @Override
    public T find(ID id) {
        return this.getDao().find(id);
    }
    
}
