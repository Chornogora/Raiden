package ua.nure.bulhakov.summary.database;

import java.sql.*;

public abstract class ServiceDatabaseManager extends DatabaseManager{

    private static final String INSERT =
            "INSERT INTO services(service_name) VALUES (?)";

    protected int insertService(Connection connection, String serviceName) throws SQLException {
        PreparedStatement statement = null;
        ResultSet keys;

        statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, serviceName);
        statement.execute();

        keys = statement.getGeneratedKeys();
        if(keys.next()){
            return keys.getInt(1);
        }
        return -1;
    }
}
