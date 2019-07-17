package ua.nure.bulhakov.summary.controller.launch;

import com.itextpdf.text.DocumentException;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.service.contract.Scheduler;
import ua.nure.bulhakov.summary.service.document.PDFCreator;

/**
 * Main launcher, that calls other launchers and starts daily checking
 * @author A.Bulhakov
 */
public class ApplicationLauncher implements Launcher{

    private static ApplicationLauncher instance;

    private ApplicationLauncher(){

    }

    public static ApplicationLauncher getInstance(){
        if(instance == null){
            instance = new ApplicationLauncher();
        }
        return instance;
    }

    @Override
    public void config(String root){
        try {
            LoggerLauncher.getInstance().config(root);
            DatabaseLauncher.getInstance().config(root);
            ResourceLauncher.getInstance().config(root);
            SocketLauncher.getInstance().config(root);
            EmailLauncher.getInstance().config(root);
        }catch(LaunchException e){
            throw new RuntimeLaunchException("Can't launch program", e);
        }
        Scheduler.getInstance().start();
        try{
            PDFCreator.getInstance().generateDocument(root);
        }catch(DocumentException e){
            throw new RuntimeLaunchException("Can't generate PDF document", e);
        }catch(DBException e){
            throw new RuntimeLaunchException("Can't access database", e);
        }
    }

}
