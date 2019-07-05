package ua.nure.bulhakov.summary.controller.administrator.adding;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Internet;
import ua.nure.bulhakov.summary.service.administrator.ServiceAdder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrator/internet/adding")
public class InternetAddingServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(InternetAddingServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String speed = request.getParameter("speed");
        String price = request.getParameter("price");

        Internet inet = new Internet();
        inet.setName(name);
        inet.setSpeed(Integer.parseInt(speed));
        inet.setMonthPrice(Double.parseDouble(price));

        try {
            new ServiceAdder().addInternet(inet);
        }catch(DBException e){
            logger.error("Can't add internet", e);
            response.sendError(500);
        }
    }

}