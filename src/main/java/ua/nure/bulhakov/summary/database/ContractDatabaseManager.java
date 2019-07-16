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

    private static final String SELECT = "SELECT * FROM contracts WHERE contract_id = ?";

    private static final String SELECT_CONTRACTS_BY_CLIENT = "SELECT client_fullname, contracts.contract_id, contract_address, contract_connected, contract_status, contract_control, services.service_id, service_name" +
            " FROM ((clients LEFT JOIN contracts ON clients.client_id = contracts.client_id)" +
            " LEFT JOIN contract_services ON contracts.contract_id = contract_services.contract_id)" +
            " INNER JOIN services ON contract_services.service_id = services.service_id" +
            " WHERE clients.client_id = ?;";

    private static final String SELECT_CONTRACTS_BY_SERVICE = "SELECT contracts.contract_id, contract_address, contract_connected, contract_status, contract_control" +
            " FROM (contracts LEFT JOIN contract_services ON contracts.contract_id = contract_services.contract_id)" +
            " INNER JOIN services ON contract_services.service_id = services.service_id" +
            " WHERE services.service_id = ?";

    private static final String GET_SERVICE_PRICE = "SELECT internet.internet_month_price AS price FROM services INNER JOIN internet ON services.service_id = internet.service_id WHERE services.service_id = ?\n" +
            "UNION\n" +
            "SELECT phone_connection_month_price AS price FROM services INNER JOIN phone_connection ON services.service_id = phone_connection.service_id WHERE services.service_id = ?\n" +
            "UNION\n" +
            "SELECT television_month_price AS price FROM services INNER JOIN television ON services.service_id = television.service_id WHERE services.service_id = ?\n";

    private static final String INSERT_ITEM = "INSERT INTO contract_services VALUES(?, ?)";

    private static final String DELETE_ITEMS = "DELETE FROM contract_services WHERE contract_id = ?";

    private static final String DELETE_CONTRACT = "DELETE FROM contractS WHERE contract_id = ?";

    private static final String UPDATE = "UPDATE contracts SET contract_control = ?, contract_status = ? WHERE contract_id = ?";

    private static ContractDatabaseManager instance;

    private ContractDatabaseManager(){

    }

    public static ContractDatabaseManager getInstance(){
        if(instance == null){
            instance = new ContractDatabaseManager();
        }
        return instance;
    }

    public Contract findById(int id) throws DBException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Contract contract;

        try{
            connection = ROLES.CLIENT.getConnection();

            statement = connection.prepareStatement(SELECT);
            statement.setInt(1, id);
            set = statement.executeQuery();

            if(!set.next()){
                return null;
            }

            contract = getStandardContract(set);


            contract.setClient(ClientDatabaseManager.getInstance().findById(set.getInt("client_id")));

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

        return contract;
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
            logger.error("Error in getting contracts", e);
            throw new DBException("Error in getting contracts", e);
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

    private Contract getStandardContract(ResultSet set) throws SQLException{
        Contract contract = new Contract();
        contract.setId(set.getInt("contract_id"));
        contract.setConnected(set.getDate("contract_connected"));
        contract.setControl(set.getDate("contract_control"));
        contract.setStatus(Contract.STATUS.valueOf(set.getString("contract_status")));
        contract.setAddress(set.getString("contract_address"));
        return contract;

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

    public void updateStatus(Contract contract) throws DBException{
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ROLES.ADMINISTRATOR.getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setDate(1, new Date(contract.getControl().getTime()));
            statement.setString(2, contract.getStatus().name());
            statement.setInt(3, contract.getId());
            statement.execute();

        } catch (SQLException e) {
            logger.error("Error in updating contract", e);
            throw new DBException("Error in updating contract", e);
        } finally {
            try {
                closeConnection(connection);
                closeStatement(statement);
            } catch (SQLException e) {
                //Impossible to do something;
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
            rollbackConnection(connection);
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

    private void newContract(ResultSet set, List<Contract> result) throws SQLException{
        result.add(getContract(set));
        result.get(result.size()-1).setControl(set.getDate("contract_control"));
        result.get(result.size()-1).setId(set.getInt("contract_id"));
        result.get(result.size()-1).setServices(new ArrayList<>());
    }

    public List<Contract> findByClientId(int clientId) throws DBException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<Contract> result = new ArrayList<>();

        try{
            connection = ROLES.CLIENT.getConnection();
            statement = connection.prepareStatement(SELECT_CONTRACTS_BY_CLIENT);
            statement.setInt(1, clientId);
            set = statement.executeQuery();

            if(set.next()) {
                newContract(set, result);
                do {
                    if(set.getInt("contract_id") != result.get(result.size()-1).getId()){
                        newContract(set, result);
                    }
                    Service service = new Service(){};
                    service.setId(set.getInt("service_id"));
                    service.setName(set.getString("service_name"));
                    result.get(result.size()-1).getServices().add(service);
                } while (set.next());
            }

            for(Contract contract : result){
                double monthPrice = 0;
                for(Service service : contract.getServices()){
                    statement = connection.prepareStatement(GET_SERVICE_PRICE);
                    statement.setInt(1, service.getId());
                    statement.setInt(2, service.getId());
                    statement.setInt(3, service.getId());
                    set = statement.executeQuery();

                    if(set.next()){
                        monthPrice += set.getDouble("price");
                    }
                }
                contract.setMonthPrice(monthPrice);
            }

            return result;
        } catch (SQLException e) {
            logger.error("Error in getting contracts by client id", e);
            throw new DBException("Error in getting contracts by client id", e);
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

    public List<Contract> findByServiceId(int serviceId) throws DBException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<Contract> result = new ArrayList<>();

        try{
            connection = ROLES.ADMINISTRATOR.getConnection();
            statement = connection.prepareStatement(SELECT_CONTRACTS_BY_SERVICE);
            statement.setInt(1, serviceId);
            set = statement.executeQuery();

            while(set.next()) {
                Contract contract = getStandardContract(set);

                result.add(contract);
            }
            return result;
        } catch (SQLException e) {
            logger.error("Error in getting contracts by service id", e);
            throw new DBException("Error in getting contracts by service id", e);
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
}