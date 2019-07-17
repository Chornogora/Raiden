package ua.nure.bulhakov.summary.service.contract;

import ua.nure.bulhakov.summary.database.ClientDatabaseManager;
import ua.nure.bulhakov.summary.database.ContractDatabaseManager;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Client;
import ua.nure.bulhakov.summary.model.Contract;

import java.time.LocalDate;
import java.time.ZoneId;
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
        boolean isPaid = true;
        for(Contract contract : contracts){
            if(client.getStatus() != Client.STATUS.BLOCKED && !updateContract(client, contract)){
                isPaid = false;
            }
        }

        if(!isPaid){
            client.setStatus(Client.STATUS.TIMED_BLOCKED);
            ClientDatabaseManager.getInstance().updateStatus(client.getId(), client.getStatus());
        }else if(client.getStatus() == Client.STATUS.TIMED_BLOCKED){
            client.setStatus(Client.STATUS.NORMAL);
            ClientDatabaseManager.getInstance().updateStatus(client.getId(), client.getStatus());
        }
    }

    private boolean updateContract(Client client, Contract contract) throws DBException{
        if(contract.getControl().compareTo(new Date(System.currentTimeMillis())) <= 0){
            if(contract.getMonthPrice() <= client.getAccount()) {
                if (contract.getStatus() != Contract.STATUS.CONNECTED) {
                    contract.setStatus(Contract.STATUS.CONNECTED);
                }

                LocalDate date = LocalDate.now().plusMonths(1);
                Date future = Date.from(date.atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant());
                contract.setControl(future);
                client.setAccount(client.getAccount() - contract.getMonthPrice());
                ContractDatabaseManager.getInstance().updateStatus(contract);
                ClientDatabaseManager.getInstance().updateAccount(client.getAccount(), client.getId());

                return true;
            }else{
                contract.setStatus(Contract.STATUS.BLOCKED);
                ContractDatabaseManager.getInstance().updateStatus(contract);
                return false;
            }
        }
        return true;
    }

}