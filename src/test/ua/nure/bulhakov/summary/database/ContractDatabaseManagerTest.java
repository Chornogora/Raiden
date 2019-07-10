package ua.nure.bulhakov.summary.database;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.nure.bulhakov.summary.controller.launch.ApplicationLauncher;
import ua.nure.bulhakov.summary.model.Contract;

import java.util.List;

public class ContractDatabaseManagerTest {

    @Before
    public void setUp(){
        String s = System.getProperty("user.dir");
        ApplicationLauncher.getInstance().config(s+"/src/main/webapp");
    }

    @Test
    public void getAllContracts() throws DBException{
        List<Contract> contracts = ContractDatabaseManager.getInstance().findAll();
        System.out.println(contracts.size() == 3);
    }

}
