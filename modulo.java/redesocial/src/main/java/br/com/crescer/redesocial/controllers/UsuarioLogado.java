package br.com.crescer.redesocial.controllers;
  
import br.com.crescer.redesocial.entidades.Usuario;
import br.com.crescer.redesocial.services.UsersService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

/**
 * @author lucas.muller
 */
@Component
public class UsuarioLogado {
    
    @Autowired
    private UsersService usersService;
    
    public Usuario getUsuarioLogado() {
        try {
            return Optional.ofNullable(SecurityContextHolder.getContext())
                    .map(SecurityContext::getAuthentication)
                    .map(Authentication::getPrincipal)
                    .map(User.class::cast)
                    .map(User::getUsername)
                    .map(usersService::findByEmail)
                    .orElse(null);
        } catch(ClassCastException ex) {
            return null;
        }
    }
    
}
