package filters;

import users.cdi.LoginBean;
import util.MessagesNames;
import util.MessagesUtils;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kacper on 2014-10-16.
 */
public class AuthorizationFilter implements Filter {

    @Inject
    LoginBean loginBean;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (loginBean == null || !loginBean.isLogged()) {
            response.sendRedirect(request.getContextPath()
                    + "/views/unsecure/users/access-denied.xhtml");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
