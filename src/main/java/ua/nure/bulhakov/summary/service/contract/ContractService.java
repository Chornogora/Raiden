package ua.nure.bulhakov.summary.service.contract;

import ua.nure.bulhakov.summary.database.ContractDatabaseManager;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Contract;

import java.util.Date;
import java.util.List;

public class ContractService {

    public void addContract(Contract contract) throws DBException {
        contract.setConnected(new Date(System.currentTimeMillis()));
        contract.setControl(contract.getConnected());
        contract.setStatus(Contract.STATUS.BLOCKED);
        ContractDatabaseManager.getInstance().insert(contract);
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
