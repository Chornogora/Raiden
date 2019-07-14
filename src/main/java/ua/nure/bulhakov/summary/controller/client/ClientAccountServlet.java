package ua.nure.bulhakov.summary.controller.client;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Client;
import ua.nure.bulhakov.summary.service.client.ClientAccount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/client/account")
public class ClientAccountServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ClientAccountServlet.class);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = (Client)req.getSession().getAttribute("client");
        double value = Double.parseDouble(req.getParameter("amount"));

        try {
            new ClientAccount().increaseAccount(client, value);
            req.getSession().setAttribute("client", client);
            resp.sendRedirect("/Raiden_war/pages/Client/Account.jsp");
        }catch(DBException e){
            logger.error("Can't increase account", e);
            resp.sendError(500);
        }
    }
}
