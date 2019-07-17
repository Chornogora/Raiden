package ua.nure.bulhakov.summary.controller.chat;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class that allows users to get program port and connect to sockets
 * @author A.Bulhakov
 */
@WebServlet("/chat")
public class ChatServlet extends HttpServlet {

    /**
     * Method that allows administrator to join chat.
     * Writes a number of port to administrator.
     * @param req servletRequest
     * @param resp servletResponse
     * @throws IOException while using HttpServletResponse#getWriter()
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int port = SocketDispatcher.getChatPort();
        resp.getWriter().print(port);
    }

    /**
     * Method that allows client to create chat
     * Writes a number of port to client.
     * @param req servletRequest
     * @param resp servletResponse
     * @throws IOException while using HttpServletResponse#getWriter()
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int port = SocketDispatcher.createChatSocket();
        resp.getWriter().print(port);
    }
}
