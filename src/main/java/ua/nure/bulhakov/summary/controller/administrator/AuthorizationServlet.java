package ua.nure.bulhakov.summary.controller.administrator;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.service.administrator.Authenticable;
import ua.nure.bulhakov.summary.service.administrator.Authentication;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrator/authorization")
public class AuthorizationServlet extends HttpServlet {

    private static final Logger logger = Logger.getRootLogger();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Authentication service = new Authentication();
        try {
            Authenticable.STATUS status = service.authenticate(login, password);
            switch(status){
                case FALSE:
                    response.setStatus(204);
                    break;
                case TRUE:
                    logger.trace("Admin authorized: " + login);
                    request.getSession().setAttribute("login", login);
                    response.setStatus(200);
                    break;
                default: response.setStatus(400);
            }
        }catch(DBException e){
            logger.error("Error in authentication", e);
            response.sendError(500);
        }

    }

}