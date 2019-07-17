package ua.nure.bulhakov.summary.controller.administrator.clients;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.service.administrator.ClientService;
import ua.nure.bulhakov.summary.model.Client;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class that allows administrator to update user information
 * @author A.Bulhakov
 */
@WebServlet("/administrator/client/updating")
public class ClientUpdatingServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ClientUpdatingServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String clientString = req.getParameter("client");
        Gson gson = new Gson();
        Client client = gson.fromJson(clientString, Client.class);

        try {
            new ClientService().updateClient(client);
        } catch (DBException e) {
            logger.error("Can't update client");
            resp.sendError(500);
            return;
        }

        resp.setStatus(200);
    }
}
