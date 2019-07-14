package ua.nure.bulhakov.summary.service.client;

import ua.nure.bulhakov.summary.database.ClientDatabaseManager;
import ua.nure.bulhakov.summary.database.DBException;
import ua.nure.bulhakov.summary.model.Client;

public class ClientAccount {
    public void increaseAccount(Client client, double value) throws DBException {
        client.setAccount(client.getAccount() + value);
        ClientDatabaseManager.getInstance().updateAccount(client.getAccount(), client.getId());
    }
}