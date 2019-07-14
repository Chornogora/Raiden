package ua.nure.bulhakov.summary.controller.administrator.services.adding;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.controller.administrator.services.DocumentGeneratingServlet;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Television;
import ua.nure.bulhakov.summary.service.administrator.ServiceAdder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrator/television/adding")
public class TelevisionAddingServlet extends DocumentGeneratingServlet {

    private static final Logger logger = Logger.getLogger(TelevisionAddingServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String format = req.getParameter("format");
        int channels = 0;
        double price = 0D;
        try {
            channels = Integer.parseInt(req.getParameter("channels"));
            price = Double.parseDouble(req.getParameter("price"));
        }catch(NumberFormatException e){
            logger.error("Bad request while adding PhoneConnection");
            resp.sendError(400);
        }

        Television television = new Television();
        television.setName(name);
        television.setFormat(format);
        television.setChannels(channels);
        television.setMonthPrice(price);

        try {
            new ServiceAdder().addTelevision(television);
            req.getRequestDispatcher("/pages/Administrator/Success.html").forward(req, resp);
        }catch(DBException e){
            resp.sendError(500);
        }

        generateDocument();
    }
}