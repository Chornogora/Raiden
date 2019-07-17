package ua.nure.bulhakov.summary.controller.administrator.clients;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Client;
import ua.nure.bulhakov.summary.service.administrator.ClientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * class that allows administrator to get clients, change their status or delete them
 * @author A.Bulhakov
 */
@WebServlet("/administrator/client")
public class ClientServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ClientServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients;

        try {
            clients = new ClientService().getAllClients();
        } catch (DBException e) {
            logger.error("Can't find clients");
            resp.sendError(500);
            return;
        }

        req.setAttribute("clients", clients);
        req.getRequestDispatcher("/pages/Administrator/Clients/ClientPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = getId(req);
        Client.STATUS status = Client.STATUS.valueOf(req.getParameter("status"));

        try{
            new ClientService().changeStatus(id, status);
        }catch (DBException e){
            logger.error("Can't change client's status");
            resp.sendError(500);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = getId(req);
        try{
            if(!new ClientService().deleteClient(id)){
                resp.sendError(403);
            }
        }catch(DBException e){
            logger.error("Can't delete client");
            resp.sendError(500);
        }
    }

    private int getId(HttpServletRequest req){
        String strId = req.getParameter("id");
        return Integer.parseInt(strId);
    }
}
