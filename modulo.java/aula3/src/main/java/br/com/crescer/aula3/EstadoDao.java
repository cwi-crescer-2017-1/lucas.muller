/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas.muller
 */
public class EstadoDao implements Dao<Estado> {
    private static final String INSERT = "insert into estado (id, nome, uf, pais) values (?, ?, ?, ?)";
    private static final String DELETE = "delete estado where id = ?";
    private static final String UPDATE = "update estado set nome = ?, uf = ?, pais = ? where id = ?";
    private static final String LOADBYID = "select * from estado where id = ?";

    @Override
    public void insert(Estado t) {
        try (final PreparedStatement statement = ConnectionUtils.prepareStatement(INSERT)) {
            statement.setInt(1, t.getId());
            statement.setString(2, t.getNome());
            statement.setString(3, t.getUf());
            statement.setInt(4, t.getPais());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Estado t) {
        try (final PreparedStatement statement = ConnectionUtils.prepareStatement(DELETE)) {
            statement.setInt(1, t.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Estado t) {
        try (final PreparedStatement statement = ConnectionUtils.prepareStatement(UPDATE)) {
            statement.setString(1, t.getNome());
            statement.setString(2, t.getUf());
            statement.setInt(3, t.getPais());
            statement.setInt(4, t.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Estado loadBy(int id) {
        try (final PreparedStatement statement = ConnectionUtils.prepareStatement(LOADBYID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                return new Estado(
                        result.getInt("ID"),
                        result.getString("NOME"),
                        result.getString("UF"),
                        result.getInt("PAIS")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
