package ua.nure.bulhakov.summary.database;

import java.sql.*;

abstract class ServiceDatabaseManager extends DatabaseManager{

    private static final String INSERT =
            "INSERT INTO services(service_name) VALUES (?)";

    private static final String UPDATE_SERVICE = "UPDATE services SET service_name = ? WHERE service_id = ?";

    private static final String DELETE_SERVICE = "DELETE FROM services WHERE service_id = ?";

    int insertService(Connection connection, String serviceName) throws SQLException {
        PreparedStatement statement = null;
        ResultSet keys = null;

        try {
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, serviceName);
            statement.execute();

            keys = statement.getGeneratedKeys();

            if (keys.next()) {
                return keys.getInt(1);
            }
            return -1;
        }finally{
            closeStatement(statement);
            closeResultSet(keys);
        }
    }

    void updateService(Connection connection, String serviceName, int id) throws SQLException{
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_SERVICE);
            statement.setString(1, serviceName);
            statement.setInt(2, id);
            statement.execute();
        }finally{
            closeStatement(statement);
        }

    }

    void deleteService(Connection connection, int id) throws SQLException{
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(DELETE_SERVICE);
            statement.setInt(1, id);
            statement.execute();
        }finally {
            closeStatement(statement);
        }
    }
}
