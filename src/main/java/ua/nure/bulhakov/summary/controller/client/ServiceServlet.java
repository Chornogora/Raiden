package ua.nure.bulhakov.summary.controller.client;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.service.administrator.ServiceGetter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/client/services")
public class ServiceServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ServiceServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceGetter getter = new ServiceGetter();
        try {
            req.setAttribute("internet", getter.getInternet());
            req.setAttribute("television", getter.getTelevision());
            req.setAttribute("telephony", getter.getPhone());
            req.setAttribute("works", getter.getWorks());
        }catch (DBException e){
            logger.error("Can't load services", e);
            resp.sendError(500);
        }
        req.getRequestDispatcher("/pages/Client/Services.jsp").forward(req, resp);
    }
}
