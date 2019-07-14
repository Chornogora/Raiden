package ua.nure.bulhakov.summary.controller.administrator.services.adding;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.controller.administrator.services.DocumentGeneratingServlet;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Internet;
import ua.nure.bulhakov.summary.service.administrator.ServiceAdder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrator/internet/adding")
public class InternetAddingServlet extends DocumentGeneratingServlet {

    private final Logger logger = Logger.getLogger(InternetAddingServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String speed = request.getParameter("speed");
        String price = request.getParameter("price");

        Internet inet = new Internet();
        inet.setName(name);
        inet.setSpeed(Integer.parseInt(speed));
        inet.setMonthPrice(Double.parseDouble(price));

        try {
            new ServiceAdder().addInternet(inet);
            request.getRequestDispatcher("/pages/Administrator/Success.html").forward(request, response);
        }catch(DBException e){
            logger.error("Can't add internet");
            response.sendError(500);
        }

        generateDocument();

    }

}