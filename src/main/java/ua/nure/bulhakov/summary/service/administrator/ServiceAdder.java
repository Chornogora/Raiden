package ua.nure.bulhakov.summary.service.administrator;

import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.database.InternetDatabaseManager;
import ua.nure.bulhakov.summary.model.Internet;

public class ServiceAdder {

    public void addInternet(Internet inet) throws DBException {
        InternetDatabaseManager.getInstance().insert(inet);
    }

}
