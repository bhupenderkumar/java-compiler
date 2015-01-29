package filters;

import users.cdi.LoginBean;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Kacper on 2014-10-20.
 */
public class AdminFilter implements Filter {

    @Inject
    private LoginBean loginBean;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (!loginBean.role("ADMINISTRATOR")) {
            response.sendRedirect(request.getContextPath() + "/views/unsecure/not-found.xhtml");
        }  else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
