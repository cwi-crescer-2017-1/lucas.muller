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
public class PaisDao implements Dao<Pais> {
    private static final String INSERT = "insert into pais (id, nome, sigla) values (?, ?, ?)";
    private static final String DELETE = "delete pais where id = ?";
    private static final String UPDATE = "update pais set nome = ?, sigla = ? where id = ?";
    private static final String LOADBYID = "select * from pais where id = ?";

    @Override
    public void insert(Pais t) {
        try (final PreparedStatement statement = ConnectionUtils.prepareStatement(INSERT)) {
            statement.setInt(1, t.getId());
            statement.setString(2, t.getNome());
            statement.setString(3, t.getSigla());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Pais t) {
        try (final PreparedStatement statement = ConnectionUtils.prepareStatement(DELETE)) {
            statement.setInt(1, t.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Pais t) {
        try (final PreparedStatement statement = ConnectionUtils.prepareStatement(UPDATE)) {
            statement.setString(1, t.getNome());
            statement.setString(2, t.getSigla());
            statement.setInt(3, t.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Pais loadBy(int id) {
        try (final PreparedStatement statement = ConnectionUtils.prepareStatement(LOADBYID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                return new Pais(
                        result.getInt("ID"),
                        result.getString("NOME"),
                        result.getString("SIGLA")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
