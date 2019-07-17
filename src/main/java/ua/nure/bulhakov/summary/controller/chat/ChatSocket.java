package ua.nure.bulhakov.summary.controller.chat;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import javax.websocket.server.ServerEndpoint;
import java.net.InetSocketAddress;


/**
 * @author A.Bulhakov
 */
@ServerEndpoint(value = "/point")
public class ChatSocket extends WebSocketServer {

    /**
     * Port, on which ChatSocket is opening.
     * This port should be released while closing ChatSocket
     */
    private int port;

    /**
     * client connection
     */
    private WebSocket client;

    /**
     * admin connection
     */
    private WebSocket admin;

    ChatSocket(String hostName, int p) {
        super(new InetSocketAddress(hostName, p));
        port = p;
    }

    /**
     * Method, that adds new connection. Sets client or admin connection
     * @param webSocket userConnection
     */
    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        if(client == null){
            client = webSocket;
        }else{
            admin = webSocket;
            client.send("Admin");
        }
    }

    /**
     * Method that notify one of users that another one had left.
     * Releases port.
     * @param webSocket user that has left
     * @see SocketDispatcher
     */
    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        if(webSocket == client){
            admin.send("SYSTEM: client left");
        }else{
            client.send("SYSTEM: admin left");
        }
        SocketDispatcher.freePort(port);
    }

    /**
     * Method for exchanging messages between users
     * @param webSocket author of message
     */
    @Override
    public void onMessage(WebSocket webSocket, String s) {
        if(webSocket == client){
            admin.send(s);
        }else{
            client.send(s);
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        //Error will not be thrown
    }

    @Override
    public void onStart() {
        //This part is not needed
    }
}
