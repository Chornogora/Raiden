package ua.nure.bulhakov.summary.database;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDatabaseManager extends DatabaseManager{

    private static final String SELECT_ALL = "SELECT * FROM clients";

    private static final String SELECT = "SELECT * FROM clients WHERE client_id = ?";

    private static ClientDatabaseManager instance;

    private static final Logger logger = Logger.getLogger(ClientDatabaseManager.class);

    private static final String INSERT = "INSERT INTO clients" +
            "(client_password, client_fullname, client_passport_series, client_passport_number, client_account, " +
            "client_phone_number, client_email, client_status) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE clients SET client_fullname = ?, client_passport_series=?, " +
            "client_passport_number=?, client_phone_number=?, client_email=? WHERE client_id = ?";

    private static final String UPDATE_ACCOUNT = "UPDATE clients SET client_account = ? WHERE client_id = ?";

    private static final String UPDATE_STATUS = "UPDATE clients SET client_status = ? WHERE client_id = ?";

    private static final String DELETE = "DELETE FROM clients WHERE client_id = ?";

    private ClientDatabaseManager(){

    }

    public static ClientDatabaseManager getInstance(){
        if(instance == null){
            instance = new ClientDatabaseManager();
        }
        return instance;
    }

    public void updateStatus(int id, Client.STATUS targetStatus) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ROLES.ADMINISTRATOR.getConnection();

            statement = connection.prepareStatement(UPDATE_STATUS);
            statement.setString(1, targetStatus.name());
            statement.setInt(2, id);

            statement.execute();
        } catch (SQLException e) {
            logger.error("Error in updating client", e);
            throw new DBException("Error in updating client", e);
        } finally {
            try {
                closeConnection(connection);
                closeStatement(statement);
            } catch (SQLException e) {
                //Impossible to do something;
            }
        }
    }

    public void updateAccount(double value, int id) throws DBException{
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ROLES.CLIENT.getConnection();

            statement = connection.prepareStatement(UPDATE_ACCOUNT);
            statement.setDouble(1, value);
            statement.setInt(2, id);
            statement.execute();

        } catch (SQLException e) {
            logger.error("Error in updating client account", e);
            throw new DBException("Error in updating client account", e);
        } finally {
            try {
                closeConnection(connection);
                closeStatement(statement);
            } catch (SQLException e) {
                //Impossible to do something;
            }
        }
    }

    public Client findById(int id) throws DBException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Client client = null;

        try{
            connection = ROLES.ADMINISTRATOR.getConnection();
            statement = connection.prepareStatement(SELECT);
            statement.setInt(1, id);
            set = statement.executeQuery();

            if(set.next()){
                client = getClient(set);
            }
        } catch (SQLException e) {
            logger.error("Error in selecting clients", e);
            throw new DBException("Error in selecting clients", e);
        } finally {
            try {
                closeConnection(connection);
                closeStatement(statement);
                closeResultSet(set);
            } catch (SQLException e) {
                //Impossible to do something;
            }
        }

        return client;
    }

    private Client getClient(ResultSet set) throws SQLException{
        Client client = new Client();
        client.setId(set.getInt("client_id"));
        client.setPassword(set.getString("client_password"));
        client.setFullName(set.getString("client_fullname"));
        client.setPassportSeries(set.getString("client_passport_series"));
        client.setPassportNumber(set.getInt("client_passport_number"));
        client.setAccount(set.getDouble("client_account"));
        client.setPhoneNumber(set.getString("client_phone_number"));
        client.setEmail(set.getString("client_email"));
        client.setStatus(Client.STATUS.valueOf(set.getString("client_status")));

        return client;
    }

    public List<Client> findAll() throws DBException{
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        List<Client> list = new ArrayList<>();

        try{
            connection = ROLES.ADMINISTRATOR.getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery(SELECT_ALL);

            while(set.next()){
                Client client = getClient(set);

                list.add(client);
            }
        } catch (SQLException e) {
            logger.error("Error in selecting clients", e);
            throw new DBException("Error in selecting clients", e);
        } finally {
            try {
                closeConnection(connection);
                closeStatement(statement);
                closeResultSet(set);
            } catch (SQLException e) {
                //Impossible to do something;
            }
        }

        return list;
    }

    public void insert(Client client) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ROLES.ADMINISTRATOR.getConnection();

            statement = connection.prepareStatement(INSERT);
            statement.setString(1, client.getPassword());
            statement.setString(2, client.getFullName());
            statement.setString(3, client.getPassportSeries());
            statement.setInt(4, client.getPassportNumber());
            statement.setDouble(5, client.getAccount());
            statement.setString(6, client.getPhoneNumber());
            statement.setString(7, client.getEmail());
            statement.setString(8, client.getStatus().name());
            statement.execute();

        } catch (SQLException e) {
            logger.error("Error in creating client", e);
            throw new DBException("Error in creating client", e);
        } finally {
            try {
                closeConnection(connection);
                closeStatement(statement);
            } catch (SQLException e) {
                //Impossible to do something;
            }
        }
    }

    public void update(Client client) throws DBException{
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ROLES.ADMINISTRATOR.getConnection();

            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, client.getFullName());
            statement.setString(2, client.getPassportSeries());
            statement.setInt(3, client.getPassportNumber());
            statement.setString(4, client.getPhoneNumber());
            statement.setString(5, client.getEmail());
            statement.setInt(6, client.getId());

            statement.execute();
        } catch (SQLException e) {
            logger.error("Error in updating client", e);
            throw new DBException("Error in updating client", e);
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
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
          logger.error("Error in deleting client", e);
          throw new DBException("Error in deleting client", e);
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