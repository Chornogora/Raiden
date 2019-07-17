package ua.nure.bulhakov.summary.service.email;

import org.junit.Before;
import org.junit.Test;
import ua.nure.bulhakov.summary.controller.launch.ApplicationLauncher;
import ua.nure.bulhakov.summary.controller.launch.RuntimeLaunchException;

public class EmailSenderTest {

    @Before
    public void setUp(){
        String s = System.getProperty("user.dir");
        try {
            ApplicationLauncher.getInstance().config(s + "/src/main/webapp/");
        }catch(RuntimeLaunchException e){
            //do nothing
        }
    }

    @Test
    public void sendExisted() throws EmailException {
        EmailSender.sendThroughRemote("", "", "raidensupport@ukr.net");
    }

    @Test(expected = EmailException.class)
    public void sendNonExisted() throws EmailException {
        EmailSender.sendThroughRemote("", "", "tellmesupport@ukr.net");
    }
}
