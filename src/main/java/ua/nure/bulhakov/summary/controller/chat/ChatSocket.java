package ua.nure.bulhakov.summary.controller.chat;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import javax.websocket.server.ServerEndpoint;
import java.net.InetSocketAddress;

@ServerEndpoint(value = "/point")
public class ChatSocket extends WebSocketServer {

    private int port;

    private WebSocket client;

    private WebSocket admin;

    ChatSocket(String hostName, int p) {
        super(new InetSocketAddress(hostName, p));
        port = p;
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        if(client == null){
            client = webSocket;
        }else{
            admin = webSocket;
            client.send("Admin");
        }
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        if(webSocket == client){
            admin.send("SYSTEM: client left");
        }else{
            client.send("SYSTEM: admin left");
        }
        SocketDispatcher.freePort(port);
    }

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

    }

    @Override
    public void onStart() {

    }
}
