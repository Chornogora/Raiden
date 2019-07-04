package ua.nure.bulhakov.summary.controller.launch;

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
        }catch(LaunchException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

}
