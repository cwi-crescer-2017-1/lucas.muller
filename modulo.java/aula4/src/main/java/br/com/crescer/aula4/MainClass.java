/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula4;

import javax.persistence.*;

/**
 *
 * @author lucas.muller
 */
public class MainClass {
    
    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        final EntityManager em = emf.createEntityManager();
        
        final Cliente cliente = new Cliente(1L, "Lucas");
        
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        
        em.close();
        emf.close();
    }
    
}
