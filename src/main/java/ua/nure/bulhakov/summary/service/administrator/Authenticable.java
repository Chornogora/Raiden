package ua.nure.bulhakov.summary.service.administrator;

import ua.nure.bulhakov.summary.database.DBException;

public interface Authenticable {

    STATUS authenticate(String login, String password) throws DBException;

    public enum STATUS{
        TRUE, FALSE, UNDEFINED
    }
}
