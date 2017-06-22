/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula3;

/**
 *
 * @author lucas.muller
 */
public interface Dao<T> {
    void insert(T t);
    void delete(T t);
    void update(T t);
    T loadBy(int id);
}
