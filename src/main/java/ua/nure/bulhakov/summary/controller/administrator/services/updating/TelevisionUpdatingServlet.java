package ua.nure.bulhakov.summary.controller.administrator.services.updating;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Television;
import ua.nure.bulhakov.summary.service.administrator.ServiceUpdater;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrator/television/updating")
public class TelevisionUpdatingServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(TelevisionUpdatingServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String format = request.getParameter("format");
        int channels = 0;
        double price = 0D;
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            channels = Integer.parseInt(request.getParameter("channels"));
            price = Double.parseDouble(request.getParameter("price"));
        }catch(NumberFormatException e){
            logger.error("Bad request while adding PhoneConnection");
            response.sendError(400);
        }

        Television television = new Television();
        television.setId(id);
        television.setName(name);
        television.setFormat(format);
        television.setChannels(channels);
        television.setMonthPrice(price);

        try {
            new ServiceUpdater().updateTelevision(television);
            request.getRequestDispatcher("/pages/Administrator/Success.html").forward(request, response);
        }catch(DBException e){
            logger.error("Can't update internet");
            response.sendError(500);
        }
    }
}