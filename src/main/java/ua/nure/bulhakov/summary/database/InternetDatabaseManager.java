package ua.nure.bulhakov.summary.database;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.model.Internet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InternetDatabaseManager extends ServiceDatabaseManager {

    private static final Logger logger = Logger.getLogger(InternetDatabaseManager.class);

    private static final String INSERT = "INSERT INTO internet VALUES(?, ?, ?)";

    private static final String UPDATE = "UPDATE internet SET internet_speed = ?, internet_month_price = ? WHERE service_id = ?";

    private static final String DELETE = "DELETE FROM internet WHERE service_id = ?";

    private static final String SELECT_ALL =
            "SELECT internet.service_id AS service_id, internet_speed, internet_month_price, service_name FROM internet, services" +
                    " WHERE internet.service_id = services.service_id ";

    private static InternetDatabaseManager instance;

    private InternetDatabaseManager() {

    }

    public static InternetDatabaseManager getInstance() {
        if (instance == null) {
            instance = new InternetDatabaseManager();
        }
        return instance;
    }

    public List<Internet> findAll() throws DBException{
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        List<Internet> result = new ArrayList<>();

        try{
            connection = ROLES.ADMINISTRATOR.getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery(SELECT_ALL);

            while(set.next()){
                Internet inet = new Internet();
                inet.setId(set.getInt("service_id"));
                inet.setName(set.getString("service_name"));
                inet.setMonthPrice(set.getDouble("internet_month_price"));
                inet.setSpeed(set.getInt("internet_speed"));

                result.add(inet);
            }

            return result;
        }catch(SQLException e){
            logger.error("Error in getting internet tariffs", e);
            throw new DBException("Error in getting internet tariffs", e);
        }finally {
            try {
                closeConnection(connection);
                closeStatement(statement);
                closeResultSet(set);
            } catch (SQLException e) {
                //Impossible to do something;
            }
        }
    }

    public void insert(Internet inet) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ROLES.ADMINISTRATOR.getConnection();
            connection.setAutoCommit(false);

            int serviceKey = insertService(connection, inet.getName());
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(2, inet.getSpeed());
            statement.setDouble(3, inet.getMonthPrice());
            statement.setInt(1, serviceKey);
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

    public void update(Internet inet) throws DBException{
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = ROLES.ADMINISTRATOR.getConnection();
            connection.setAutoCommit(false);

            updateService(connection, inet.getName(), inet.getId());
            statement = connection.prepareStatement(UPDATE);
            statement.setInt(1, inet.getSpeed());
            statement.setDouble(2, inet.getMonthPrice());
            statement.setInt(3, inet.getId());
            statement.execute();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            rollbackConnection(connection);
            logger.error("Error in updating internet tariff", e);
            throw new DBException("Error in updating internet tariff", e);
        } finally {
            try {
                closeConnection(connection);
                closeStatement(statement);
            } catch (SQLException e) {
                //Impossible to do something;
            }
        }
    }

    public void delete(int id) throws DBException{
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = ROLES.ADMINISTRATOR.getConnection();
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            statement.execute();

            deleteService(connection, id);

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            rollbackConnection(connection);
            logger.error("Error in deleting internet tariff", e);
            throw new DBException("Error in deleting internet tariff", e);
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
