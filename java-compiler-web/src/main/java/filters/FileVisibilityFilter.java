package filters;

import file_visibilities.enitites.FileVisibility;
import files.dao.FileDao;
import files.entities.File;
import users.cdi.LoginBean;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Kacper on 2014-10-20.
 */
public class FileVisibilityFilter implements Filter {

    @Inject
    private LoginBean loginBean;

    @Inject
    private FileDao fileDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String param = request.getParameter("id");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (param != null) {
            int id = Integer.parseInt(param);
            FileVisibility fv = fileDao.getFileVisibility(id);
            File file = fileDao.getFileById(id);
            if (fv.getId() == (long)1 && (!loginBean.role("ADMINISTRATOR") || loginBean.getId() != file.getUserId())) {
                response.sendRedirect(request.getContextPath() + "/views/unsecure/not-found.xhtml");
            } else {
                filterChain.doFilter(request, response);
            }
        }  else {
            filterChain.doFilter(request, response);
        }
    }


    @Override
    public void destroy() {

    }
}
