package ua.nure.bulhakov.summary.controller.launch;

import ua.nure.bulhakov.summary.service.email.EmailSender;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EmailLauncher implements Launcher{
    private static EmailLauncher instance;

    private EmailLauncher(){

    }

    static EmailLauncher getInstance(){
        if(instance == null){
            instance = new EmailLauncher();
        }
        return instance;
    }

    @Override
    public void config(String rootPath) throws LaunchException {
        try {
            Properties props = new Properties();
            props.load(new FileReader(rootPath + "/WEB-INF/mail.properties"));
            EmailSender.setProperties(props);
            EmailSender.setSender(props.getProperty("user"));
            EmailSender.setPassword(props.getProperty("password"));
        } catch (IOException e) {
            throw new LaunchException("Can't find mail.properties", e);
        }
    }
}
