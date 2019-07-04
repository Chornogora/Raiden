package ua.nure.bulhakov.summary.service.administrator;

import ua.nure.bulhakov.summary.database.AdministratorDatabaseManager;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Administrator;

public class Authentication implements Authenticable{

    @Override
    public Authenticable.STATUS authenticate(String login, String password) throws DBException {
        Administrator admin = AdministratorDatabaseManager.getInstance().findByLogin(login);
        if(admin == null) {
            return STATUS.UNDEFINED;
        }

        return (Encoder.getInstance().compare(password, admin.getPassword())) ? STATUS.TRUE : STATUS.FALSE;
    }

}
