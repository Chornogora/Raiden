package ua.nure.bulhakov.summary.controller;

import ua.nure.bulhakov.summary.controller.launch.ApplicationLauncher;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        ApplicationLauncher.getInstance().config(context.getRealPath(""));
    }

}
