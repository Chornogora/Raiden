package ua.nure.bulhakov.summary.controller;

import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.service.contract.ContractUpdater;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new ContractUpdater().updateAll();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
