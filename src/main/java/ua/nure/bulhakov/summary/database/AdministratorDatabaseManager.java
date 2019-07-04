package ua.nure.bulhakov.summary.database;

import java.sql.*;
import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.model.Administrator;
import ua.nure.bulhakov.summary.service.administrator.Encoder;

public class AdministratorDatabaseManager extends DatabaseManager {

    private static final Logger logger = Logger.getLogger(AdministratorDatabaseManager.class);

    private static final String SELECT_ONE = "SELECT * FROM administrators WHERE administrator_login = ?";
    private static final String INSERT =
            "INSERT INTO administrators(administrator_login, administrator_password, administrator_fullname) VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM administrators WHERE administrator_id = ?";

    private static AdministratorDatabaseManager instance;

    public static AdministratorDatabaseManager getInstance(){
        if(instance == null){
            instance = new AdministratorDatabaseManager();
        }
        return instance;
    }

    public Administrator findByLogin(String login) throws DBException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Administrator result = null;

        try{
            Class.forName(driverName);
            connection = DriverManager.getConnection(connectionString, CLASS_LEVEL.ADMINISTRATOR.getLogin(), CLASS_LEVEL.ADMINISTRATOR.getPassword());
            statement = connection.prepareStatement(SELECT_ONE);
            statement.setString(1, login);
            set = statement.executeQuery();

            //if there isn't administrator with such login
            if(set.next()){
                result = new Administrator();
                result.setId(set.getInt("administrator_id"));
                result.setFullName(set.getString("administrator_fullname"));
                result.setLogin(set.getString("administrator_login"));
                result.setPassword(set.getString("administrator_password"));
            }

        }catch(SQLException e){
            logger.error("Error during getting administrator", e);
            throw new DBException("Error during getting administrator", e);
        }catch(ClassNotFoundException e){
            logger.error("JDBC driver not found", e);
            throw new DBException("JDBC driver not found", e);
        }finally{
            try {
                closeConnection(connection);
                closeStatement(statement);
                closeResultSet(set);
            }catch(SQLException e){
                //Impossible to do something;
            }
        }

        return result;
    }

    public void insert(Administrator administrator) throws DBException{
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            Class.forName(driverName);
            connection = DriverManager.getConnection(connectionString, CLASS_LEVEL.BOSS.getLogin(), CLASS_LEVEL.BOSS.getPassword());
            statement = connection.prepareStatement(INSERT);
            statement.setString(1, administrator.getLogin());
            statement.setString(2, administrator.getPassword());
            statement.setString(3, administrator.getFullName());
            statement.execute();

            //if there isn't administrator with such login
        }catch(SQLException e){
            logger.error("Error in creating administrator", e);
            throw new DBException("Error in creating administrator", e);
        }catch(ClassNotFoundException e){
            logger.error("JDBC driver not found", e);
            throw new DBException("JDBC driver not found", e);
        }finally{
            try {
                closeConnection(connection);
                closeStatement(statement);
            }catch(SQLException e){
                //Impossible to do something;
            }
        }
    }

    public void delete(int id) throws DBException{
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            Class.forName(driverName);
            connection = DriverManager.getConnection(connectionString, CLASS_LEVEL.BOSS.getLogin(), CLASS_LEVEL.BOSS.getPassword());
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            statement.execute();

            //if there isn't administrator with such login
        }catch(SQLException e){
            logger.error("Error in deleting administrator", e);
            throw new DBException("Error in deleting administrator", e);
        }catch(ClassNotFoundException e){
            logger.error("JDBC driver not found", e);
            throw new DBException("JDBC driver not found", e);
        }finally{
            try {
                closeConnection(connection);
                closeStatement(statement);
            }catch(SQLException e){
                //Impossible to do something;
            }
        }
    }
}