package ua.nure.bulhakov.summary.service.administrator;

import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.database.InternetDatabaseManager;
import ua.nure.bulhakov.summary.model.Internet;

public class ServiceUpdater {

    public void updateInternet(Internet inet) throws DBException {
        InternetDatabaseManager.getInstance().update(inet);
    }

}
