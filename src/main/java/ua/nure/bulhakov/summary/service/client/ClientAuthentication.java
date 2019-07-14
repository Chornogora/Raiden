package ua.nure.bulhakov.summary.service.client;

import ua.nure.bulhakov.summary.database.ContractDatabaseManager;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Client;
import ua.nure.bulhakov.summary.model.Contract;
import ua.nure.bulhakov.summary.service.Authenticable;
import ua.nure.bulhakov.summary.service.administrator.Encoder;

public class ClientAuthentication implements Authenticable {

    private Client cash;

    @Override
    public STATUS authenticate(String idString, String password) throws DBException {
        Contract contract = ContractDatabaseManager.getInstance().findById(Integer.parseInt(idString));
        if(contract != null){
            Client client = contract.getClient();
            String codedPass = client.getPassword();
            if(Encoder.getInstance().compare(password, codedPass)){
                cash = client;
                return STATUS.TRUE;
            }else{
                return STATUS.FALSE;
            }
        }else{
            return STATUS.UNDEFINED;
        }
    }

    public Client getCash(){
        return cash;
    }

}
