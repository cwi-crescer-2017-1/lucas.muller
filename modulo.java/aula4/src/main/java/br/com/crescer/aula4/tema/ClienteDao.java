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
public class ClienteDao implements CrudDao<Cliente, Long> {

    @Override
    public Cliente save(Cliente e) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        
        session.saveOrUpdate(e);
        return e;
    }

    @Override
    public void remove(Cliente e) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        
        session.delete(e);
    }

    @Override
    public Cliente loadById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        
        Cliente cliente = (Cliente) session.load(Cliente.class, id);
        return cliente;
    }

    @Override
    public List<Cliente> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Cliente.class);
        
        List<Cliente> clientes = criteria.list();
        return clientes;
    }
    
}
