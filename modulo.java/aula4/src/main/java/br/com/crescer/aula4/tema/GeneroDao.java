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
 * @author Lucas
 */
public class GeneroDao implements CrudDao<Genero, Long> {

    @Override
    public Genero save(Genero e) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        
        session.saveOrUpdate(e);
        return e;
    }

    @Override
    public void remove(Genero e) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        
        session.delete(e);
    }

    @Override
    public Genero loadById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        
        return (Genero) session.load(Genero.class, id);
    }

    @Override
    public List<Genero> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Genero.class);
        
        return criteria.list();
    }
    
}
