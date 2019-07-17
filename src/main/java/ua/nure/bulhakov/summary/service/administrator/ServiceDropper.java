package ua.nure.bulhakov.summary.service.administrator;

import ua.nure.bulhakov.summary.database.*;
import ua.nure.bulhakov.summary.service.email.database.*;
import ua.nure.bulhakov.summary.model.Contract;
import ua.nure.bulhakov.summary.service.contract.ContractService;

import java.util.List;

public class ServiceDropper {

    public boolean dropInternet(int id) throws DBException {
        List<Contract> list = new ContractService().getServiceContracts(id);
        if(!list.isEmpty()){
            return false;
        }
        InternetDatabaseManager.getInstance().delete(id);
        return true;
    }

    public boolean dropPhoneConnection(int id) throws DBException {
        List<Contract> list = new ContractService().getServiceContracts(id);
        if(!list.isEmpty()){
            return false;
        }
        PhoneConnectionDatabaseManager.getInstance().delete(id);
        return true;
    }

    public boolean dropTelevision(int id) throws DBException {
        List<Contract> list = new ContractService().getServiceContracts(id);
        if(!list.isEmpty()){
            return false;
        }
        TelevisionDatabaseManager.getInstance().delete(id);
        return true;
    }

    public boolean dropWork(int id) throws DBException {
        List<Contract> list = new ContractService().getServiceContracts(id);
        if(!list.isEmpty()){
            return false;
        }
        WorkDatabaseManager.getInstance().delete(id);
        return true;
    }
}