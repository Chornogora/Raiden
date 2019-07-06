package ua.nure.bulhakov.summary.service.administrator;

import ua.nure.bulhakov.summary.database.*;
import ua.nure.bulhakov.summary.model.Internet;
import ua.nure.bulhakov.summary.model.PhoneConnection;
import ua.nure.bulhakov.summary.model.Television;
import ua.nure.bulhakov.summary.model.Work;

import java.util.List;

public class ServiceGetter {

    public List<Internet> getInternet() throws DBException {
        return InternetDatabaseManager.getInstance().findAll();
    }

    public List<PhoneConnection> getPhone() throws DBException {
        return PhoneConnectionDatabaseManager.getInstance().findAll();
    }

    public List<Television> getTelevision() throws DBException {
        return TelevisionDatabaseManager.getInstance().findAll();
    }

    public List<Work> getWorks() throws DBException {
        return WorkDatabaseManager.getInstance().findAll();
    }

}
