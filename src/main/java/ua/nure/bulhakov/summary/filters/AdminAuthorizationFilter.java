package ua.nure.bulhakov.summary.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminAuthorizationFilter")
public class AdminAuthorizationFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String str = request.getRequestURI();
        if(request.getSession().getAttribute("admin") != null || str.equals("/Raiden_war/pages/Administrator/AdminAuthorization.html")) {
            chain.doFilter(req, resp);
        }else{
            ((HttpServletResponse) resp).sendRedirect("/Raiden_war/pages/Administrator/AdminAuthorization.html");
        }
    }

}
