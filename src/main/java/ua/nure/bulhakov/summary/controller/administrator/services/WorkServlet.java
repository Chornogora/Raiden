package ua.nure.bulhakov.summary.controller.administrator.services;

import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Work;
import ua.nure.bulhakov.summary.service.administrator.ServiceDropper;
import ua.nure.bulhakov.summary.service.administrator.ServiceGetter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/administrator/service")
public class WorkServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Work> lst = new ServiceGetter().getWorks();
            request.setAttribute("list", lst);
            request.getRequestDispatcher("/pages/Administrator/ServicePage.jsp").forward(request, response);
        }catch(DBException e){
            response.sendError(500);
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        }catch(NumberFormatException e){
            response.sendError(500);
        }

        try{
            new ServiceDropper().DropWork(id);
        }catch(DBException e){
            response.sendError(500);
        }
    }
}