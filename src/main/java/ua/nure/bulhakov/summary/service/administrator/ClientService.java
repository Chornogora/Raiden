package ua.nure.bulhakov.summary.service.administrator;

import ua.nure.bulhakov.summary.database.ClientDatabaseManager;
import ua.nure.bulhakov.summary.database.ContractDatabaseManager;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Client;
import ua.nure.bulhakov.summary.model.Contract;

import java.util.List;

public class ClientService {

    public void addClient(Client client) throws DBException{
        String codedPassword = Encoder.getInstance().encode(client.getPassword());
        client.setPassword(codedPassword);
        ClientDatabaseManager.getInstance().insert(client);
    }

    public List<Client> getAllClients() throws DBException{
        return ClientDatabaseManager.getInstance().findAll();
    }

    public void updateClient(Client client) throws DBException{
        ClientDatabaseManager.getInstance().update(client);
    }

    public boolean deleteClient(int id) throws DBException{
        List<Contract> list = ContractDatabaseManager.getInstance().findByClientId(id);
        if(!list.isEmpty()){
            return false;
        }
        ClientDatabaseManager.getInstance().delete(id);
        return true;
    }

    public void changeStatus(int id, Client.STATUS status) throws DBException {
        Client.STATUS targetStatus;
        switch(status){
            case NORMAL:
                targetStatus = Client.STATUS.BLOCKED;
                break;
            case BLOCKED:
                targetStatus = Client.STATUS.NORMAL;
                break;
            case TIMED_BLOCKED:
                targetStatus = Client.STATUS.BLOCKED;
                break;
            //never will be used
            default: throw new IllegalArgumentException();
        }
        ClientDatabaseManager.getInstance().updateStatus(id, targetStatus);
    }
}
