package ua.nure.bulhakov.summary.database;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.model.Work;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkDatabaseManager extends ServiceDatabaseManager {

    private static final Logger logger = Logger.getLogger(WorkDatabaseManager.class);

    private static final String INSERT = "INSERT INTO work VALUES(?, ?, ?)";

    private static final String SELECT_ALL =
            "SELECT work.service_id AS service_id, service_name, work_measure, work_price FROM work, services" +
                    " WHERE work.service_id = services.service_id ";
    private static final String UPDATE = "UPDATE work SET work_measure=?, work_price=? WHERE service_id = ?";
    private static final String DELETE = "DELETE FROM work WHERE service_id=?";

    private static WorkDatabaseManager instance;

    private WorkDatabaseManager() {

    }

    public static WorkDatabaseManager getInstance() {
        if (instance == null) {
            instance = new WorkDatabaseManager();
        }
        return instance;
    }

    public List<Work> findAll() throws DBException{
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        List<Work> result = new ArrayList<>();

        try{
            connection = ROLES.ADMINISTRATOR.getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery(SELECT_ALL);

            while(set.next()){
                Work work = new Work();
                work.setId(set.getInt("service_id"));
                work.setName(set.getString("service_name"));
                work.setMeasure(set.getString("work_measure"));
                work.setPrice(set.getDouble("work_price"));

                result.add(work);
            }

            return result;
        }catch(SQLException e){
            logger.error("Error in getting work list", e);
            throw new DBException("Error in getting work list", e);
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

    public void insert(Work work) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ROLES.ADMINISTRATOR.getConnection();
            connection.setAutoCommit(false);

            int serviceKey = insertService(connection, work.getName());
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, serviceKey);
            statement.setString(2, work.getMeasure());
            statement.setDouble(3, work.getPrice());
            statement.execute();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Error in creating work", e);
            throw new DBException("Error in creating work", e);
        } finally {
            try {
                closeConnection(connection);
                closeStatement(statement);
            } catch (SQLException e) {
                //Impossible to do something;
            }
        }
    }

    public void update(Work work) throws DBException{
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = ROLES.ADMINISTRATOR.getConnection();
            connection.setAutoCommit(false);

            updateService(connection, work.getName(), work.getId());
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, work.getMeasure());
            statement.setDouble(2, work.getPrice());
            statement.setInt(3, work.getId());
            statement.execute();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            rollbackConnection(connection);
            logger.error("Error in updating work", e);
            throw new DBException("Error in updating work", e);
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
            logger.error("Error in deleting television tariff", e);
            throw new DBException("Error in deleting television tariff", e);
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
