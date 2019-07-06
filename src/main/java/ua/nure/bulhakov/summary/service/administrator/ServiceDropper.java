package ua.nure.bulhakov.summary.service.administrator;

import ua.nure.bulhakov.summary.database.*;

public class ServiceDropper {

    public void DropInternet(int id) throws DBException {
        InternetDatabaseManager.getInstance().delete(id);
    }

    public void DropPhoneConnection(int id) throws DBException {
        PhoneConnectionDatabaseManager.getInstance().delete(id);
    }

    public void DropTelevision(int id) throws DBException {
        TelevisionDatabaseManager.getInstance().delete(id);
    }

    public void DropWork(int id) throws DBException {
        WorkDatabaseManager.getInstance().delete(id);
    }
}
