package ua.nure.bulhakov.summary.database;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.model.Internet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InternetDatabaseManager extends ServiceDatabaseManager {

    private static final Logger logger = Logger.getLogger(InternetDatabaseManager.class);

    private static final String INSERT = "INSERT INTO internet VALUES(?, ?, ?)";

    private static InternetDatabaseManager instance;

    private InternetDatabaseManager() {

    }

    public static InternetDatabaseManager getInstance() {
        if (instance == null) {
            instance = new InternetDatabaseManager();
        }
        return instance;
    }

    public void insert(Internet inet) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ROLES.ADMINISTRATOR.getConnection();
            connection.setAutoCommit(false);

            int serviceKey = insertService(connection, inet.getName());
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, serviceKey);
            statement.setInt(2, inet.getSpeed());
            statement.setDouble(3, inet.getMonthPrice());
            statement.execute();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Error in creating internet tariff", e);
            throw new DBException("Error in creating internet tariff", e);
        } finally {
            try {
                closeConnection(connection);
                closeStatement(statement);
            } catch (SQLException e) {
                //Impossible to do something;
            }
        }
    }
}
