package ua.nure.bulhakov.summary.controller.launch;

import ua.nure.bulhakov.summary.controller.chat.SocketDispatcher;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SocketLauncher implements Launcher{

    private static SocketLauncher instance;

    private SocketLauncher(){

    }

    public static SocketLauncher getInstance(){
        if(instance == null){
            instance = new SocketLauncher();
        }
        return instance;
    }

    @Override
    public void config(String rootPath) throws LaunchException {
        try {
            Properties props = new Properties();
            props.load(new FileReader(rootPath + "/WEB-INF/socketProps.properties"));
            SocketDispatcher.init(props.getProperty("hostname"));
        }catch(IOException e){
            throw new LaunchException("Error in configuring Socket Dispatcher", e);
        }
    }
}
