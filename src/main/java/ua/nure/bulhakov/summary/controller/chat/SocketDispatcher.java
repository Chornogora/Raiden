package ua.nure.bulhakov.summary.controller.chat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.LinkedList;

/**
 * Static class that controls program ports
 * @author A.Bulhakov
 */
public class SocketDispatcher {

    //private static final Logger logger = Logger.getLogger(SocketDispatcher.class);

    /**
     * name from config, that is using to create Sockets
     */
    private static String hostName;

    /**
     * list of free program ports
     */
    private static LinkedList<Integer> ports;

    /**
     * list of busy program ports
     */
    private static LinkedList<Integer> busyPorts;

    private SocketDispatcher(){

    }

    /**
     * Setting hostname and filling ports array.
     * This method is called from launcher
     * @param name hostname e.g. localhost
     */
    public static void init(String name){
        hostName = name;
        ports = new LinkedList<>();
        busyPorts = new LinkedList<>();

        for(int i = 1200; i < 65535; ++i) {
            try(ServerSocket ss = new ServerSocket()){
                ss.bind(new InetSocketAddress(InetAddress.getByName(hostName), i), 1);
                ports.addLast(i);
            }catch (IOException ex) {
                //Go to the next port
            }
        }
    }

    /**
     * Find's first empty port from list.
     * Create new ChatSocket with hostname and this port.
     * @return number of the first empty port or -1
     * @see ChatSocket
     */
    public static int createChatSocket(){
        if(ports.isEmpty()){
            return -1;
        }
        int port = ports.removeFirst();
        ChatSocket wss = new ChatSocket(hostName, port);
        busyPorts.push(port);
        wss.start();
        return port;
    }

    /**
     * @return number of the busy empty port or -1
     */
    public static int getChatPort(){
        if(busyPorts.isEmpty()){
            return -1;
        }
        return busyPorts.removeFirst();

    }

    /**
     * Adds port to the list of free ports.
     * Method is called when ChatSocket is closing
     * @param port that should be free
     * @see ChatSocket
     */
    static void freePort(int port){
        ports.addLast(port);
        busyPorts.remove((Integer)port);
    }

}