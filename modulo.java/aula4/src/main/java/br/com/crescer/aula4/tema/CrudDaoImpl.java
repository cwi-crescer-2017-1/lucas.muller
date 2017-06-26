/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula4.tema;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author lucas.muller
 * @param <Entity>
 * @param <ID>
 */
public class CrudDaoImpl<Entity, ID> implements CrudDao<Entity, ID> {
    private final Class<Entity> classe;
    
    public CrudDaoImpl (Class<Entity> classe) {
        this.classe = classe;
    }

    @Override
    public Entity save(Entity e) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return e;
    }

    @Override
    public void remove(Entity e) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @Override
    public Entity loadById(ID id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        EntityManager em = emf.createEntityManager();
        Entity e = (Entity) em.find(this.classe, id);
        em.close();
        emf.close();
        return e;
    }

    @Override
    public List<Entity> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        EntityManager em = emf.createEntityManager();
        Session sessao = em.unwrap(Session.class);
        Criteria criteria = sessao.createCriteria(this.classe);
        List<Entity> e = criteria.list();
        em.close();
        emf.close();
        return e;
    }
    
}
