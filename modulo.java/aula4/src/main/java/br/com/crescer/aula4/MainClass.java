/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula4;

import br.com.crescer.aula4.tema.Cliente;
import br.com.crescer.aula4.tema.CrudDaoImpl;

/**
 *
 * @author lucas.muller
 */
public class MainClass {
    
    public static void main(String[] args) {
        CrudDaoImpl<Cliente, Long> clienteDao = new CrudDaoImpl<>(Cliente.class);
//        Cliente cliente = clienteDao.loadById(4L);
//        cliente.setNome("Lucas Müller");
//        cliente.setCpf("123456");
//        cliente.setCelular("9989854");
//        clienteDao.save(cliente);
        clienteDao.remove(clienteDao.loadById(5L));
        clienteDao.findAll().stream().map(e -> e.getNome()).forEach(System.out::println);
    }
    
}
