package ua.nure.bulhakov.summary.controller.administrator.services.adding;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.controller.administrator.services.DocumentGeneratingServlet;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.PhoneConnection;
import ua.nure.bulhakov.summary.service.administrator.ServiceAdder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrator/phone/adding")
public class PhoneAddingServlet extends DocumentGeneratingServlet {

    private static final Logger logger = Logger.getLogger(PhoneAddingServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        double price = 0D;
        int mobileMin = 0;
        try {
            price = Double.parseDouble(req.getParameter("price"));
            mobileMin = Integer.parseInt(req.getParameter("minutes"));
        }catch(NumberFormatException e){
            logger.error("Bad request while adding PhoneConnection");
            resp.sendError(400);
        }

        PhoneConnection phone = new PhoneConnection();
        phone.setName(name);
        phone.setMonthPrice(price);
        phone.setMobileMinutes(mobileMin);

        try {
            new ServiceAdder().addPhone(phone);
            req.getRequestDispatcher("/pages/Administrator/Success.html").forward(req, resp);
        }catch(DBException e){
            resp.sendError(500);
        }

        generateDocument();
    }
}