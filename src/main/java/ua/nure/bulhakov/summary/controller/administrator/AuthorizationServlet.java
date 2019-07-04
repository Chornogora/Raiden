package ua.nure.bulhakov.summary.controller.administrator;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/administrator/authorization")
public class AuthorizationServlet extends HttpServlet {

    private static final Logger logger = Logger.getRootLogger();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        logger.info("Request!");

        request.getParameter("login");
        request.getParameter("password");
    }

}