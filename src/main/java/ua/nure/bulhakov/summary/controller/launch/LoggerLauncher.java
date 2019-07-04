package ua.nure.bulhakov.summary.controller.launch;

import org.apache.log4j.PropertyConfigurator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class LoggerLauncher implements Launcher{

    private static LoggerLauncher instance;

    private LoggerLauncher(){

    }

    public static LoggerLauncher getInstance(){
        if(instance == null){
            instance = new LoggerLauncher();
        }
        return instance;
    }

    @Override
    public void config(String root) throws LaunchException{
        String fullPath = root + "/WEB-INF/log4j.properties";
        Properties props = new Properties();
        try {
            props.load(new FileReader(fullPath));
        }catch(FileNotFoundException e){
            throw new LaunchException("Can't find file 'log4j.properties", e);
        } catch(IOException e) {
            throw new LaunchException("Can't generate properties using 'log4j.properties'", e);
        }

        PropertyConfigurator.configure(props);
    }
}
