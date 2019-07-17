package ua.nure.bulhakov.summary.controller.administrator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class that allows to invalidate administrator session
 * @author A.Bulhakov
 */
@WebServlet("/administrator/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("/Raiden_war/");
    }

}
