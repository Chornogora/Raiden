package ua.nure.bulhakov.summary.controller.administrator.services;

import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Internet;
import ua.nure.bulhakov.summary.service.administrator.ServiceGetter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/administrator/internet")
public class InternetServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Internet> lst = new ServiceGetter().getInternet();
            request.setAttribute("list", lst);
            request.getRequestDispatcher("/pages/InternetPage.jsp").forward(request, response);
        }catch(DBException e){
            response.sendError(500);
        }
    }

}