package ua.nure.bulhakov.summary.controller.administrator.services.updating;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Work;
import ua.nure.bulhakov.summary.service.administrator.ServiceUpdater;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrator/service/updating")
public class WorkUpdatingServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(WorkUpdatingServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        String name = req.getParameter("name");
        String measure = req.getParameter("measure");
        double price = 0D;
        try {
            id = Integer.parseInt(req.getParameter("id"));
            price = Double.parseDouble(req.getParameter("price"));
        }catch(NumberFormatException e){
            logger.error("Bad request while adding PhoneConnection");
            resp.sendError(400);
        }

        Work work = new Work();
        work.setId(id);
        work.setName(name);
        work.setPrice(price);
        work.setMeasure(measure);

        try {
            new ServiceUpdater().updateWork(work);
            req.getRequestDispatcher("/pages/Administrator/Success.html").forward(req, resp);
        }catch(DBException e){
            resp.sendError(500);
        }
    }
}