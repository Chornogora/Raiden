package ua.nure.bulhakov.summary.controller.chat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.LinkedList;

public class SocketDispatcher {

    //private static final Logger logger = Logger.getLogger(SocketDispatcher.class);

    private static String hostName;

    private static LinkedList<Integer> ports;

    private static LinkedList<Integer> busyPorts;

    private SocketDispatcher(){

    }

    public static void init(String name){
        hostName = name;
        ports = new LinkedList<>();
        busyPorts = new LinkedList<>();
        /**/

        //<Checking port>
        for(int i = 1200; i < 65535; ++i) {
            try(ServerSocket ss = new ServerSocket()){
                ss.bind(new InetSocketAddress(InetAddress.getByName(hostName), i), 1);
                ports.addLast(i);
            }catch (IOException ex) {
                //Go to the next port
            }
        }
        //</Checking port>
    }

    static int createChatSocket(){
        if(ports.isEmpty()){
            return -1;
        }
        int port = ports.removeFirst();
        ChatSocket wss = new ChatSocket(hostName, port);
        busyPorts.push(port);
        wss.start();
        return port;
    }

    static int getChatPort(){
        if(busyPorts.isEmpty()){
            return -1;
        }
        return busyPorts.removeFirst();

    }

    static void freePort(int port){
        ports.addLast(port);
        busyPorts.remove((Integer)port);
    }

}