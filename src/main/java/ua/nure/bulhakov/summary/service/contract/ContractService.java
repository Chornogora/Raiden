package ua.nure.bulhakov.summary.service.contract;

import ua.nure.bulhakov.summary.database.ContractDatabaseManager;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Client;
import ua.nure.bulhakov.summary.model.Contract;

import java.util.Date;
import java.util.List;

public class ContractService {

    public boolean addContract(Contract contract) throws DBException {
        contract.setConnected(new Date(System.currentTimeMillis()));
        contract.setControl(contract.getConnected());
        contract.setStatus(Contract.STATUS.BLOCKED);

        if(contract.getClient().getStatus() != Client.STATUS.NORMAL){
            return false;
        }
        ContractDatabaseManager.getInstance().insert(contract);
        return true;
    }

    public List<Contract> getServiceContracts(int serviceId) throws DBException{
        return ContractDatabaseManager.getInstance().findByServiceId(serviceId);
    }

    public List<Contract> getContracts() throws DBException{
        return ContractDatabaseManager.getInstance().findAll();
    }

    public void deleteContract(Contract contract) throws DBException{
        ContractDatabaseManager.getInstance().delete(contract);
    }

    public List<Contract> getUserContracts(int userId) throws DBException{
        return ContractDatabaseManager.getInstance().findByClientId(userId);
    }

}
