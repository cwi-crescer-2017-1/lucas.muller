package br.com.crescer.aula6.utils;

import java.io.Serializable;

/**
 * @author carloshenrique
 */
public interface UserDetails extends Serializable {

    public static final String USER_AUTH = "USER_AUTH";

    public String getUsername();

}
