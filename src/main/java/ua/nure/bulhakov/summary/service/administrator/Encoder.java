package ua.nure.bulhakov.summary.service.administrator;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoder {

    private static Encoder instance;

    private MessageDigest digest;

    private Encoder(){
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            //Unreachable code
        }
    }

    public static Encoder getInstance(){
        if(instance == null){
            instance = new Encoder();
        }
        return instance;
    }

    public String encode(String message){
        digest.update(message.getBytes(Charset.forName("UTF-8")));
        byte[] temp = digest.digest();
        return new String(temp);
    }

    public boolean compare(String message, String codedMessage){
        return codedMessage.equals(encode(message));
    }
}
