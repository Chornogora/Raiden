package ua.nure.bulhakov.summary.service.administrator;

import ua.nure.bulhakov.summary.database.*;
import ua.nure.bulhakov.summary.service.email.database.*;
import ua.nure.bulhakov.summary.model.Internet;
import ua.nure.bulhakov.summary.model.PhoneConnection;
import ua.nure.bulhakov.summary.model.Television;
import ua.nure.bulhakov.summary.model.Work;

public class ServiceUpdater {

    public void updateInternet(Internet inet) throws DBException {
        InternetDatabaseManager.getInstance().update(inet);
    }

    public void updatePhoneConnection(PhoneConnection phone) throws DBException {
        PhoneConnectionDatabaseManager.getInstance().update(phone);
    }

    public void updateTelevision(Television television) throws DBException {
        TelevisionDatabaseManager.getInstance().update(television);
    }

    public void updateWork(Work work) throws DBException {
        WorkDatabaseManager.getInstance().update(work);
    }
}
