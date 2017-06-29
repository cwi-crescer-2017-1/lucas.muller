package br.com.crescer.redesocial.services;

import br.com.crescer.redesocial.exceptions.NotFoundException;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Lucas
 */
public class GenericService<T extends Object, ID extends Serializable, Repository extends PagingAndSortingRepository<T, ID>> implements BasicService<T, ID> {
    
    private static final int MIN_LIMIT = 10;
    
    @Autowired
    private Repository repo;

    @Override
    public Page<T> findAll(Integer page, Integer limit) {
        return repo.findAll(new PageRequest(
                page == null ? 0 : page, 
                limit == null || limit == 0 ? MIN_LIMIT : limit
        ));
    }

    @Override
    public T findByID(ID id) {
        T et = repo.findOne(id);
        if(et != null)
            return et;
        else
            throw new NotFoundException();
    }

    @Override
    public T save(T et) {
        return repo.save(et);
    }

    @Override
    public T update(ID id, T et) {
        if(repo.exists(id))
            return repo.save(et);
        else
            throw new NotFoundException();
    }

    @Override
    public void delete(ID id) {
        if(repo.exists(id))
            repo.delete(id);
        else
            throw new NotFoundException();
    }

}
