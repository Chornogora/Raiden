package ua.nure.bulhakov.summary.service.administrator;

import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.database.InternetDatabaseManager;

public class ServiceDropper {

    public void DropInternet(int id) throws DBException {
        InternetDatabaseManager.getInstance().delete(id);
    }

}
