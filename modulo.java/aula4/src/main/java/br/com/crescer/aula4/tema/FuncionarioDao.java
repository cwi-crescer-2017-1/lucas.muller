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
public class FuncionarioDao implements CrudDao<Funcionario, Long> {

    @Override
    public Funcionario save(Funcionario e) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        
        session.saveOrUpdate(e);
        return e;
    }

    @Override
    public void remove(Funcionario e) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        
        session.delete(e);
    }

    @Override
    public Funcionario loadById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        
        return (Funcionario) session.load(Funcionario.class, id);
    }

    @Override
    public List<Funcionario> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Funcionario.class);
        
        return criteria.list();
    }
    
}
