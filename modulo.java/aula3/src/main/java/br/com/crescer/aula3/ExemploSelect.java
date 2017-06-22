/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula3;

import java.sql.*;

/**
 *
 * @author lucas.muller
 */
public class ExemploSelect {

    public static void main(String[] args) {
        final String query = "select * from Pais";

        try (final Connection connection = ConnectionUtils.getConnection();
                final Statement statement = connection.createStatement();
                final ResultSet resultSet = statement.executeQuery(query);) {
            while(resultSet.next()) {
                System.out.println(resultSet.getInt("ID") + " - " +resultSet.getString("NOME"));
            }
        } catch (SQLException e) {
            System.err.format("SQLException: %s", e);
        }
    }
}
