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
public final class ConnectionUtils {
    private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String user = "AULAJAVA";
    private static final String pass = "AULAJAVA";
    
    private ConnectionUtils() { }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
    
    public static ResultSet executeQuery(String query) throws SQLException {
        try (final Statement statement = getConnection().createStatement()) {
            return statement.executeQuery(query);
        }
    }
    
    public static int executeUpdate(String query) throws SQLException {
        try (final Statement statement = getConnection().createStatement()) {
            return statement.executeUpdate(query);
        }
    }
    
    public static PreparedStatement prepareStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }
}
