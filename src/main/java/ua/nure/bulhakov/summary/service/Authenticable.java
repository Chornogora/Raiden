package ua.nure.bulhakov.summary.service;

import ua.nure.bulhakov.summary.database.DBException;

public interface Authenticable {

    STATUS authenticate(String login, String password) throws DBException;

    enum STATUS{
        TRUE, FALSE, UNDEFINED
    }
}
