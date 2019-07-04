package ua.nure.bulhakov.summary.database;

import org.junit.Before;
import org.junit.Test;
import ua.nure.bulhakov.summary.controller.launch.ApplicationLauncher;
import ua.nure.bulhakov.summary.model.Administrator;
import ua.nure.bulhakov.summary.service.administrator.Encoder;

public class AdministratorDatabaseManagerTest {

    @Before
    public void setUp(){
        String s = System.getProperty("user.dir");
        ApplicationLauncher.getInstance().config(s+"/src/main/webapp");
    }

    @Test
    public void NoteInDatabase() throws DBException{
        AdministratorDatabaseManager manager = AdministratorDatabaseManager.getInstance();
        Administrator admin = new Administrator();
        admin.setLogin("anthony228");
        admin.setFullName("Bulhakov Anton");

        String password = "anthonyPass";
        password = Encoder.getInstance().encode(password);
        admin.setPassword(password);

        manager.insert(admin);
    }
}
