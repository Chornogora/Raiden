package ua.nure.bulhakov.summary.controller.administrator.clients;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Client;
import ua.nure.bulhakov.summary.service.administrator.ClientService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrator/client/adding")
public class ClientRegistrationServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ClientRegistrationServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String clientString = req.getParameter("client");
        Gson gson = new Gson();
        Client client = gson.fromJson(clientString, Client.class);
        try {
            new ClientService().addClient(client);
        }catch(DBException e){
            logger.error("Can't add client");
            resp.sendError(500);
        }

        resp.sendRedirect("/pages/Administrator/Success.html");
    }
}