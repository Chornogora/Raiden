package ua.nure.bulhakov.summary.service.administrator;

import org.junit.Assert;
import org.junit.Test;

public class EncoderTest {

    private static final String PASSWORD = "SomePassword";

    @Test
    public void testCompare(){
        String codedPassword = Encoder.getInstance().encode(PASSWORD);
        boolean isEquals = Encoder.getInstance().compare(PASSWORD, codedPassword);
        Assert.assertTrue(isEquals);
    }

    @Test
    public void testCompare2(){
        String codedPassword = Encoder.getInstance().encode(PASSWORD);
        boolean isEquals = Encoder.getInstance().compare(PASSWORD.toLowerCase(), codedPassword);
        Assert.assertFalse(isEquals);
    }
}
