package ua.nure.bulhakov.summary.service.administrator;

import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.database.InternetDatabaseManager;
import ua.nure.bulhakov.summary.model.Internet;

import java.util.List;

public class ServiceGetter {

    public List<Internet> getInternet() throws DBException {
        return InternetDatabaseManager.getInstance().findAll();
    }

}
