package br.com.crescer.aula6.interfaces;

import java.util.List;

/**
 * @author carloshenrique
 * @param <T>
 * @param <ID>
 */
public interface CrudDao<T, ID> {

    void insert(T t);
    
    void delete(T t);
    
    T find(ID id);
    
    List<T> findAll();
}
