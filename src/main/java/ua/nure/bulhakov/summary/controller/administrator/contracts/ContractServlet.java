package ua.nure.bulhakov.summary.controller.administrator.contracts;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.*;
import ua.nure.bulhakov.summary.service.administrator.ClientService;
import ua.nure.bulhakov.summary.service.administrator.ServiceGetter;
import ua.nure.bulhakov.summary.service.contract.ContractService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/administrator/contract")
public class ContractServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ContractServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Contract> list = null;
        try {
            list = new ContractService().getContracts();
            req.setAttribute("list", list);
        } catch (DBException e) {
            logger.error("Can't get all contracts");
            resp.sendError(500);
            return;
        }

        req.getRequestDispatcher("/pages/Administrator/Contracts/ContractPage.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            Contract contract = new Contract();
            contract.setId(id);
            new ContractService().deleteContract(contract);
        } catch (DBException e) {
            logger.error("Can't delete contract");
            resp.sendError(500);
        }
    }
}