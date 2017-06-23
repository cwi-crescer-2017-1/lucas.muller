/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula3;

import br.com.crescer.aula3.tema.SQLUtilsImpl;
import java.io.File;

/**
 *
 * @author lucas.muller
 */
public class MainClass {
    public static void main(String[] args) {
//        System.out.println(new CidadeDao().loadBy(4247).getNome());
//        Cidade teste = new Cidade(9999, "Cidade teste", 10);
//        CidadeDao dao = new CidadeDao();
//        dao.insert(teste);
//        if(dao.loadBy(teste.getId()) != null) {
//            dao.delete(teste);
//            if(dao.loadBy(teste.getId()) == null) {
//                System.out.println("Sucesso!");
//            }
//        }
        new SQLUtilsImpl().exportCSV("select id, nome from cidade order by id");
        //new SQLUtilsImpl().runFile("target/pessoa.sql");
    }
}
