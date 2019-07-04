package ua.nure.bulhakov.summary.database;

import org.junit.Before;
import org.junit.Test;
import ua.nure.bulhakov.summary.controller.launch.ApplicationLauncher;
import ua.nure.bulhakov.summary.model.Administrator;

public class AdministratorDatabaseManagerTest {

    @Before
    public void setUp(){

        ApplicationLauncher.launch("");
    }

    @Test
    public void NoteInDatabase() throws DBException{
        AdministratorDatabaseManager manager = AdministratorDatabaseManager.getInstance();
        Administrator admin = new Administrator();
        admin.setLogin("Anthony");
        admin.setFullName("Bulhakov Anton");
        manager.insert(admin, "AnthonyPass");
    }
}
