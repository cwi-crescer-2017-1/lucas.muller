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
public class CidadeDao implements Dao<Cidade> {
    private static final String INSERT = "insert into cidade (id, nome, estado) values (?, ?, ?)";
    private static final String DELETE = "delete cidade where id = ?";
    private static final String UPDATE = "update cidade set nome = ?, estado = ? where id = ?";
    private static final String LOADBYID = "select * from cidade where id = ?";

    @Override
    public void insert(Cidade t) {
        try (final PreparedStatement statement = ConnectionUtils.prepareStatement(INSERT)) {
            statement.setInt(1, t.getId());
            statement.setString(2, t.getNome());
            statement.setInt(3, t.getEstado());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Cidade t) {
        try (final PreparedStatement statement = ConnectionUtils.prepareStatement(DELETE)) {
            statement.setInt(1, t.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Cidade t) {
        try (final PreparedStatement statement = ConnectionUtils.prepareStatement(UPDATE)) {
            statement.setString(1, t.getNome());
            statement.setInt(2, t.getEstado());
            statement.setInt(3, t.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Cidade loadBy(int id) {
        try (final PreparedStatement statement = ConnectionUtils.prepareStatement(LOADBYID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                return new Cidade(
                        result.getInt("ID"),
                        result.getString("NOME"),
                        result.getInt("ESTADO")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
