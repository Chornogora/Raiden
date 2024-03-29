package ua.nure.bulhakov.summary.service.administrator;

import ua.nure.bulhakov.summary.database.*;
import ua.nure.bulhakov.summary.model.Internet;
import ua.nure.bulhakov.summary.model.PhoneConnection;
import ua.nure.bulhakov.summary.model.Television;
import ua.nure.bulhakov.summary.model.Work;

public class ServiceAdder {

    public void addInternet(Internet inet) throws DBException {
        InternetDatabaseManager.getInstance().insert(inet);
    }

    public void addPhone(PhoneConnection phone) throws DBException {
        PhoneConnectionDatabaseManager.getInstance().insert(phone);
    }

    public void addTelevision(Television tel) throws DBException {
        TelevisionDatabaseManager.getInstance().insert(tel);
    }

    public void addWork(Work work) throws DBException {
       WorkDatabaseManager.getInstance().insert(work);
    }

}
