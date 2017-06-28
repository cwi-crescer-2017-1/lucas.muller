package br.com.crescer.aula6.filters;

import br.com.crescer.aula6.utils.UserDetails;

/**
 * @author carloshenrique
 */
public class User implements UserDetails {

    private String username;
    private String password;

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

}
