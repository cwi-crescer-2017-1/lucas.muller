package br.com.crescer.aula6.filters;

import static br.com.crescer.aula6.utils.UserDetails.USER_AUTH;
import br.com.crescer.aula6.utils.UserDetails;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author carloshenrique
 */
@WebFilter(filterName = "SessionFilter", urlPatterns = {"*.xhtml"})
public class SessionFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(SessionFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("init filter");
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        process((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        LOGGER.info("destroy filter");
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final HttpSession session = request.getSession();
        final UserDetails user = (UserDetails) session.getAttribute(USER_AUTH);
        if (!request.getRequestURI().contains("login.xhtml") && user == null) {
            response.sendRedirect(request.getContextPath() + "/faces/login.xhtml");
        }
    }

}
