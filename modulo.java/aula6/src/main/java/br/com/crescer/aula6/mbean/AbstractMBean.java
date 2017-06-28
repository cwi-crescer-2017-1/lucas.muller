package br.com.crescer.aula6.mbean;

import br.com.crescer.aula6.ejb.AbstractBean;
import br.com.crescer.aula6.dao.AbstractDao;
import java.io.Serializable;
import java.util.List;

/**
 * @author carloshenrique
 * @param <T>
 * @param <ID>
 * @param <DAO>
 * @param <Bean>
 */
public abstract class AbstractMBean<T, ID, DAO extends AbstractDao<T, ID>, Bean extends AbstractBean<T, ID, DAO>> implements Serializable {

    private T entity;

    private List<T> list;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public abstract Bean getBean();

    public abstract void limpar();
    
    public void adicionar() {
        this.getBean().insert(entity);
        this.limpar();
    }

}
