package ua.nure.bulhakov.summary.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ClientAuthorizationFilter")
public class ClientAuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String method = ((HttpServletRequest) servletRequest).getMethod().toLowerCase();
        if(request.getSession().getAttribute("client") != null || method.equals("post")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            ((HttpServletResponse) servletResponse).sendRedirect("/Raiden_war/pages/Client/Authorization.jsp");
        }
    }
}