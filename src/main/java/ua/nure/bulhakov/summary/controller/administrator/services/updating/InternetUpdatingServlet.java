package ua.nure.bulhakov.summary.controller.administrator.services.updating;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.controller.administrator.services.DocumentGeneratingServlet;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Internet;
import ua.nure.bulhakov.summary.service.administrator.ServiceUpdater;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrator/internet/updating")
public class InternetUpdatingServlet extends DocumentGeneratingServlet {
    private final Logger logger = Logger.getLogger(InternetUpdatingServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        String name = request.getParameter("name");
        String speed = request.getParameter("speed");
        String price = request.getParameter("price");

        try{
            id = Integer.parseInt(request.getParameter("id"));
        }catch(NumberFormatException e){
            logger.error("Can't get id");
            response.sendError(400);
            return;
        }

        Internet inet = new Internet();
        inet.setId(id);
        inet.setName(name);
        inet.setSpeed(Integer.parseInt(speed));
        inet.setMonthPrice(Double.parseDouble(price));

        try {
            new ServiceUpdater().updateInternet(inet);
            request.getRequestDispatcher("/pages/Administrator/Success.html").forward(request, response);
        }catch(DBException e){
            logger.error("Can't update internet");
            response.sendError(500);
        }

        generateDocument();
    }
}