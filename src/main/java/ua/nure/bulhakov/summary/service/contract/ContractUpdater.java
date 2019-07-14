package ua.nure.bulhakov.summary.service.contract;

import ua.nure.bulhakov.summary.database.ClientDatabaseManager;
import ua.nure.bulhakov.summary.database.ContractDatabaseManager;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Client;
import ua.nure.bulhakov.summary.model.Contract;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ContractUpdater {

    public void updateAll() throws DBException {
        List<Client> clients = ClientDatabaseManager.getInstance().findAll();
        for(Client client : clients){
            updateClientContracts(client);
        }
    }

    public void updateClientContracts(Client client)throws DBException{
        List<Contract> contracts = ContractDatabaseManager.getInstance().findByClientId(client.getId());
        for(Contract contract : contracts){
            if(contract.getControl().equals(new Date(System.currentTimeMillis()))){
                updateContract(client, contract);
            }
        }
    }

    private void updateContract(Client client, Contract contract) throws DBException{
        if(client.getStatus() != Client.STATUS.BLOCKED) {
            if(contract.getMonthPrice() <= client.getAccount()){
                contract.setStatus(Contract.STATUS.CONNECTED);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, 1);
                contract.setControl(calendar.getTime());
                client.setAccount(client.getAccount() - contract.getMonthPrice());
                ContractDatabaseManager.getInstance().updateStatus(contract);
                ClientDatabaseManager.getInstance().updateAccount(client.getAccount(), client.getId());
            }else{
                contract.setStatus(Contract.STATUS.BLOCKED);
                ContractDatabaseManager.getInstance().updateStatus(contract);
            }
        }
    }

}