package ua.nure.bulhakov.summary.controller.client;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.service.client.ClientAuthentication;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Client;
import ua.nure.bulhakov.summary.model.Contract;
import ua.nure.bulhakov.summary.service.Authenticable;
import ua.nure.bulhakov.summary.service.contract.ContractService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ClientServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = ((Client)req.getSession().getAttribute("client")).getId();
        try {
            List<Contract> contracts = new ContractService().getUserContracts(userId);
            req.setAttribute("contracts", contracts);
            req.getRequestDispatcher("/pages/Client/Cabinet2.jsp").forward(req, resp);
        } catch (DBException e) {
            logger.error("Can't get client's contracts");
            resp.sendError(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contractNumber = req.getParameter("contractNumber");
        String password = req.getParameter("password");

        ClientAuthentication service = new ClientAuthentication();

        try {
            Authenticable.STATUS status = service.authenticate(contractNumber, password);
            switch(status){
                case FALSE:
                    resp.setStatus(204);
                    break;
                case TRUE:
                    req.getSession().setAttribute("client", service.getCash());
                    resp.setStatus(200);
                    break;
                default: resp.setStatus(400);
            }
        }catch(DBException e){
            logger.error("Error in authentication", e);
            resp.sendError(500);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.getRequestDispatcher("/").forward(req, resp);
    }
}