package ua.nure.bulhakov.summary.controller.launch;

import ua.nure.bulhakov.summary.database.DatabaseManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class DatabaseLauncher implements Launcher{

    private static DatabaseLauncher instance;

    private DatabaseLauncher(){

    }

    public static DatabaseLauncher getInstance(){
        if(instance == null){
            instance = new DatabaseLauncher();
        }
        return instance;
    }

    public void config(String root) throws LaunchException {
        Properties props = new Properties();

        try {
            props.load(new FileReader(root+"WEB-INF/database.properties"));
        }catch(FileNotFoundException e){
            throw new LaunchException("Can't find file 'database.properties'", e);
        }catch(IOException e){
            throw new LaunchException("Can't generate properties using 'database.properties'", e);
        }

        DatabaseManager.setConnectionString(props.getProperty("connectionString"));

        Map<DatabaseManager.CLASS_LEVEL, String> logins = new HashMap<>();
        logins.put(DatabaseManager.CLASS_LEVEL.CLIENT, props.getProperty("clientLogin"));
        logins.put(DatabaseManager.CLASS_LEVEL.ADMINISTRATOR, props.getProperty("administratorLogin"));
        logins.put(DatabaseManager.CLASS_LEVEL.BOSS, props.getProperty("bossLogin"));
        DatabaseManager.setLoginMap(logins);

        Map<DatabaseManager.CLASS_LEVEL, String> passwords = new HashMap<>();
        passwords.put(DatabaseManager.CLASS_LEVEL.CLIENT, props.getProperty("clientPassword"));
        passwords.put(DatabaseManager.CLASS_LEVEL.ADMINISTRATOR, props.getProperty("administratorPassword"));
        passwords.put(DatabaseManager.CLASS_LEVEL.BOSS, props.getProperty("bossPassword"));
        DatabaseManager.setPasswordMap(passwords);
    }

}
