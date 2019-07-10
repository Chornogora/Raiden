package ua.nure.bulhakov.summary.database;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.model.Client;
import ua.nure.bulhakov.summary.model.Contract;
import ua.nure.bulhakov.summary.model.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContractDatabaseManager extends DatabaseManager{

    private static final Logger logger = Logger.getLogger(ContractDatabaseManager.class);
    private static final String INSERT_CONTRACT = "INSERT INTO contracts(contract_connected, contract_control, contract_status, client_id, contract_address)" +
            "VALUES(?, ?, ?, ?, ?)";

    private static final String SELECT_ALL = "SELECT contracts.contract_id, contract_address, contract_connected, contract_status, client_fullname, service_name\n" +
            "FROM contracts, contract_services, clients, services\n" +
            "WHERE contracts.client_id = clients.client_id AND contract_services.contract_id = contracts.contract_id AND\n" +
            "            contract_services.service_id = services.service_id\n" +
            "ORDER BY contracts.contract_id;";

    private static final String INSERT_ITEM = "INSERT INTO contract_services VALUES(?, ?)";

    private static final String DELETE_ITEMS = "DELETE FROM contract_services WHERE contract_id = ?";

    private static final String DELETE_CONTRACT = "DELETE FROM contractS WHERE contract_id = ?";

    private static ContractDatabaseManager instance;

    private ContractDatabaseManager(){

    }

    public static ContractDatabaseManager getInstance(){
        if(instance == null){
            instance = new ContractDatabaseManager();
        }
        return instance;
    }

    public List<Contract> findAll() throws DBException{
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        List<Contract> result = new ArrayList<>();

        try{
            connection = ROLES.ADMINISTRATOR.getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery(SELECT_ALL);

            if(set.next()){
                result = getContractsRecursively(set, result);
            }
            return result;
        } catch (SQLException e) {
            logger.error("Error in getting contract", e);
            throw new DBException("Error in getting contract", e);
        } finally {
            try {
                closeConnection(connection);
                closeStatement(statement);
                closeResultSet(set);
            } catch (SQLException e) {
                //Impossible to do something;
            }
        }

    }

    private List<Contract> getContractsRecursively(ResultSet set, List<Contract> result) throws SQLException{
        Contract contract = getContract(set);
        List<Service> services = new ArrayList<>();
        services.add(new Service() {});
        services.get(0).setName(set.getString("service_name"));

        while(set.next()) {
            if(contract.getId() != set.getInt("contract_id")){
                contract.setServices(services);
                result.add(contract);
                return getContractsRecursively(set, result);
            }else {
                Service service = new Service() {};
                service.setName(set.getString("service_name"));
                services.add(service);
            }
        }

        contract.setServices(services);
        result.add(contract);
        return result;
    }

    private Contract getContract(ResultSet set) throws SQLException{
        Contract contract = new Contract();
        Client client = new Client();
        client.setFullName(set.getString("client_fullname"));
        contract.setId(set.getInt("contract_id"));
        contract.setConnected(set.getDate("contract_connected"));
        contract.setStatus(Contract.STATUS.valueOf(set.getString("contract_status")));
        contract.setClient(client);
        contract.setAddress(set.getString("contract_address"));
        return contract;
    }

    public int insert(Contract contract) throws DBException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet keys = null;
        try{
            connection = ROLES.ADMINISTRATOR.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(INSERT_CONTRACT, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, new Date(contract.getConnected().getTime()));
            statement.setDate(2, new Date(contract.getControl().getTime()));
            statement.setString(3, contract.getStatus().name());
            statement.setInt(4, contract.getClient().getId());
            statement.setString(5, contract.getAddress());
            statement.execute();

            keys = statement.getGeneratedKeys();
            if(keys.next()) {
                contract.setId(keys.getInt(1));
            }

            insertItems(connection, contract);

            connection.commit();
            connection.setAutoCommit(true);

            return contract.getId();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            }catch (SQLException e2) {
                //Impossible to do something;
            }
            logger.error("Error in creating contract", e);
            throw new DBException("Error in creating contract", e);
        } finally {
            try {
                closeConnection(connection);
                closeStatement(statement);
                closeResultSet(keys);
            } catch (SQLException e) {
                //Impossible to do something;
            }
        }
    }

    private void insertItems(Connection connection, Contract contract)throws SQLException{
        PreparedStatement statement = null;
        List<Service> services = contract.getServices();
        for(Service service : services) {
            try {
                statement = connection.prepareStatement(INSERT_ITEM);
                statement.setInt(1, contract.getId());
                statement.setInt(2, service.getId());
                statement.execute();
            } catch (SQLException e) {
                throw new SQLException("Can't add item to contract", e);
            } finally {
                try {
                    closeStatement(statement);
                } catch (SQLException e) {
                    //Impossible to do something;
                }
            }
        }
    }

    public void delete(Contract contract) throws DBException{
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ROLES.ADMINISTRATOR.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(DELETE_ITEMS);
            statement.setInt(1, contract.getId());
            statement.execute();

            statement = connection.prepareStatement(DELETE_CONTRACT);
            statement.setInt(1, contract.getId());
            statement.execute();

            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            }catch (SQLException e2) {
                //Impossible to do something;
            }
            logger.error("Error in deleting contract", e);
            throw new DBException("Error in deleting contract", e);
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
