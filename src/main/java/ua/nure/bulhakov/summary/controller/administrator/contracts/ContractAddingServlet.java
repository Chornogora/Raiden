package ua.nure.bulhakov.summary.controller.administrator.contracts;

import com.google.gson.Gson;
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

@WebServlet("/administrator/contract/adding")
public class ContractAddingServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ContractAddingServlet.class);

    private static class RequestContract{
        private int userId;

        private String address;

        private int internet;

        private int television;

        private int phone;

        private int[] services;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("clients", new ClientService().getAllClients());
            ServiceGetter getter =new ServiceGetter();
            req.setAttribute("internetTariffs", getter.getInternet());
            req.setAttribute("televisionTariffs", getter.getTelevision());
            req.setAttribute("phoneTariffs", getter.getPhone());
            req.setAttribute("services", getter.getWorks());
        }catch(DBException e){

        }

        req.getRequestDispatcher("/pages/Administrator/Contracts/ContractAddingPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contractString = req.getParameter("contract");
        RequestContract requestContract = new Gson().fromJson(contractString, RequestContract.class);

        Contract contract = new Contract();
        contract.setAddress(requestContract.address);

        Client client = new Client();
        client.setId(requestContract.userId);
        contract.setClient(client);

        List<Service> services = new ArrayList<>();

        if(requestContract.internet != 0){
            Service inet = new Internet();
            inet.setId(requestContract.internet);
            services.add(inet);
        }

        if(requestContract.television != 0){
            Service television = new Internet();
            television.setId(requestContract.television);
            services.add(television);
        }

        if(requestContract.phone != 0){
            Service phone = new PhoneConnection();
            phone.setId(requestContract.phone);
            services.add(phone);
        }

        if(requestContract.services != null) {
            for (int temp : requestContract.services) {
                Service work = new Work();
                work.setId(temp);
                services.add(work);
            }
        }

        contract.setServices(services);

        try {
            new ContractService().addContract(contract);
        } catch (DBException e) {
            logger.error("Can't add contract");
            resp.sendError(500);
        }
    }
}