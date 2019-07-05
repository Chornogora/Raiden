package ua.nure.bulhakov.summary.controller.launch;

import ua.nure.bulhakov.summary.database.DatabaseManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

class DatabaseLauncher implements Launcher{

    private static DatabaseLauncher instance;

    private DatabaseLauncher(){

    }

    static DatabaseLauncher getInstance(){
        if(instance == null){
            instance = new DatabaseLauncher();
        }
        return instance;
    }

    public void config(String root) throws LaunchException {
        Properties props = new Properties();

        try {
            props.load(new FileReader(root+"/WEB-INF/database.properties"));
        }catch(FileNotFoundException e){
            throw new LaunchException("Can't find file 'database.properties'", e);
        }catch(IOException e){
            throw new LaunchException("Can't generate properties using 'database.properties'", e);
        }

        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource clientSource = (DataSource) envContext.lookup("jdbc/client");
            DatabaseManager.setClientSource(clientSource);
            DataSource adminSource = (DataSource) envContext.lookup("jdbc/admin");
            DatabaseManager.setAdminSource(adminSource);
        } catch (NamingException ex) {
            throw new LaunchException("Can't configurate connection pool", ex);
        }
    }

}
