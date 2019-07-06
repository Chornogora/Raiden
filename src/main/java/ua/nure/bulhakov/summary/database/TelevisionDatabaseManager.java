package ua.nure.bulhakov.summary.database;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.model.Television;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelevisionDatabaseManager extends ServiceDatabaseManager {

    private static final Logger logger = Logger.getLogger(TelevisionDatabaseManager.class);

    private static final String INSERT = "INSERT INTO television VALUES(?, ?, ?, ?)";

    private static final String SELECT_ALL =
            "SELECT television.service_id AS service_id, service_name, television_channels, television_format, television_month_price FROM television, services" +
                    " WHERE television.service_id = services.service_id ";

    private static TelevisionDatabaseManager instance;

    private TelevisionDatabaseManager() {

    }

    public static TelevisionDatabaseManager getInstance() {
        if (instance == null) {
            instance = new TelevisionDatabaseManager();
        }
        return instance;
    }

    public List<Television> findAll() throws DBException{
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        List<Television> result = new ArrayList<>();

        try{
            connection = ROLES.ADMINISTRATOR.getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery(SELECT_ALL);

            while(set.next()){
                Television television = new Television();
                television.setId(set.getInt("service_id"));
                television.setChannels(set.getInt("television_channels"));
                television.setFormat(set.getString("television_format"));
                television.setName(set.getString("service_name"));
                television.setMonthPrice(set.getDouble("television_month_price"));

                result.add(television);
            }

            return result;
        }catch(SQLException e){
            logger.error("Error in getting television tariffs", e);
            throw new DBException("Error in getting television tariffs", e);
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

    public void insert(Television television) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ROLES.ADMINISTRATOR.getConnection();
            connection.setAutoCommit(false);

            int serviceKey = insertService(connection, television.getName());
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, serviceKey);
            statement.setInt(2, television.getChannels());
            statement.setString(3, television.getFormat());
            statement.setDouble(4, television.getMonthPrice());
            statement.execute();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Error in creating television tariff", e);
            throw new DBException("Error in creating television tariff", e);
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
