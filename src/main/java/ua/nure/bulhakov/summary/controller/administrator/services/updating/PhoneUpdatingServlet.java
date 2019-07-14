package ua.nure.bulhakov.summary.controller.administrator.services.updating;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.controller.administrator.services.DocumentGeneratingServlet;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.PhoneConnection;
import ua.nure.bulhakov.summary.service.administrator.ServiceUpdater;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrator/phone/updating")
public class PhoneUpdatingServlet extends DocumentGeneratingServlet {

    private final Logger logger = Logger.getLogger(PhoneUpdatingServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        String name = request.getParameter("name");
        double monthPrice;
        int mobile;

        try{
            id = Integer.parseInt(request.getParameter("id"));
            monthPrice = Double.parseDouble(request.getParameter("monthPrice"));
            mobile = Integer.parseInt(request.getParameter("mobile"));
        }catch(NumberFormatException e){
            logger.error("Can't get parameters");
            response.sendError(400);
            return;
        }

        PhoneConnection phone = new PhoneConnection();
        phone.setId(id);
        phone.setName(name);
        phone.setMonthPrice(monthPrice);
        phone.setMobileMinutes(mobile);

        try {
            new ServiceUpdater().updatePhoneConnection(phone);
            request.getRequestDispatcher("/pages/Administrator/Success.html").forward(request, response);
        }catch(DBException e){
            logger.error("Can't update phone connection");
            response.sendError(500);
        }

        generateDocument();
    }

}