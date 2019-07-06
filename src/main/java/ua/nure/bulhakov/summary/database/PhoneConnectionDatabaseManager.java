package ua.nure.bulhakov.summary.database;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.model.PhoneConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneConnectionDatabaseManager extends ServiceDatabaseManager {

    private static final Logger logger = Logger.getLogger(PhoneConnectionDatabaseManager.class);

    private static final String INSERT = "INSERT INTO phone_connection VALUES(?, ?, ?)";

    private static final String SELECT_ALL =
            "SELECT phone_connection.service_id AS service_id, service_name, phone_connection_month_price, phone_connection_mobile_minutes FROM phone_connection, services" +
                    " WHERE phone_connection.service_id = services.service_id ";

    private static PhoneConnectionDatabaseManager instance;

    private PhoneConnectionDatabaseManager() {

    }

    public static PhoneConnectionDatabaseManager getInstance() {
        if (instance == null) {
            instance = new PhoneConnectionDatabaseManager();
        }
        return instance;
    }

    public List<PhoneConnection> findAll() throws DBException{
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        List<PhoneConnection> result = new ArrayList<>();

        try{
            connection = ROLES.ADMINISTRATOR.getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery(SELECT_ALL);

            while(set.next()){
                PhoneConnection phone = new PhoneConnection();
                phone.setId(set.getInt("service_id"));
                phone.setName(set.getString("service_name"));
                phone.setMonthPrice(set.getDouble("phone_connection_month_price"));
                phone.setMobileMinutes(set.getInt("phone_connection_mobile_minutes"));

                result.add(phone);
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

    public void insert(PhoneConnection phone) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ROLES.ADMINISTRATOR.getConnection();
            connection.setAutoCommit(false);

            int serviceKey = insertService(connection, phone.getName());
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, serviceKey);
            statement.setDouble(2, phone.getMonthPrice());
            statement.setInt(3, phone.getMobileMinutes());
            statement.execute();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Error in creating phone connection tariff", e);
            throw new DBException("Error in creating phone connection tariff", e);
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
