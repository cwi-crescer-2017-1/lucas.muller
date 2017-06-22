package br.com.crescer.aula3;

import java.sql.SQLException;

/**
 * @author carloshenrique
 */
public class ExemploDDL {

    public static void main(String[] args) {
        try {
            // DROP
            TesteDao.drop();

            // CREATE
            TesteDao.create();

            // INSERT
            TesteDao.insert();
            
        } catch (final SQLException e) {
            System.err.format("SQLException: %s", e.getMessage());
        }
    }
}