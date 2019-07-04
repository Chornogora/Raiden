package ua.nure.bulhakov.summary.database;

public interface Authenticable {

    STATUS authenticate(String login, String password) throws DBException;

    enum STATUS{
        TRUE, FALSE, UNDEFINED
    }
}
