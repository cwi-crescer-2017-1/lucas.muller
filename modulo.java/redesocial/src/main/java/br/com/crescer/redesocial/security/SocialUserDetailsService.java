package br.com.crescer.redesocial.security;

import br.com.crescer.redesocial.entidades.Usuario;
import br.com.crescer.redesocial.services.UsersService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author carloshenrique
 */
@Service
public class SocialUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersService service;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = service.findByEmail(username);
        final List<GrantedAuthority> grants = new ArrayList<>();
        grants.add(() -> "ROLE_USER");
        return new User(user.getEmail(), user.getSenha(), grants);
    }

}
