package ua.nure.bulhakov.summary.service.email;

import ua.nure.bulhakov.summary.model.Client;

public class EmailService {

    private static final String REGISTRATION_MESSAGE = "?, you were successfully added " +
            "to Raiden provider. \n\nYou password: ?. Now you can enter your personal cabinet on our " +
            "website using any of your contract number.";

    private static final String REGISTRATION_TOPIC = "Registration into Raiden";

    public boolean sendRegistrationMessage(Client client){
        StringBuilder builder = new StringBuilder(REGISTRATION_MESSAGE);
        int start = builder.indexOf("?");
        builder.replace(start, start+1, client.getFullName());
        start = builder.indexOf("?");
        builder.replace(start, start+1, client.getPassword());
        try {
            EmailSender.sendThroughRemote(REGISTRATION_TOPIC, builder.toString(), client.getEmail());
        } catch (EmailException e) {
            return false;
        }
        return true;
    }

}