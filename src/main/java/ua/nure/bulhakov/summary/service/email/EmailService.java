package ua.nure.bulhakov.summary.service.email;

import ua.nure.bulhakov.summary.model.Client;
import ua.nure.bulhakov.summary.model.Contract;

public class EmailService {

    private static final String REGISTRATION_MESSAGE = "?, you were successfully added " +
            "to Raiden provider. \n\nYou password: ?. Now you can enter your personal cabinet on our " +
            "website using any of your contract number.";

    private static final String REGISTRATION_TOPIC = "Registration into Raiden";

    private static final String CONTRACT_MESSAGE = "?, new contract were successfully connected. " +
            "\n\nContract number: ?. Now you can enter your personal cabinet on our " +
            "website using this contract number. To activate account replenish your account.";

    private static final String CONTRACT_TOPIC = "New Raiden Contract";

    public boolean sendRegistrationMessage(Client client){
        String message = getMessage(REGISTRATION_MESSAGE, client.getFullName(), client.getPassword());
        try {
            EmailSender.sendThroughRemote(REGISTRATION_TOPIC, message, client.getEmail());
        } catch (EmailException e) {
            return false;
        }
        return true;
    }

    public boolean sendNewContractMessage(Contract contract){
        String message = getMessage(CONTRACT_MESSAGE, contract.getClient().getFullName(), String.valueOf(contract.getId()));
        try {
            EmailSender.sendThroughRemote(CONTRACT_TOPIC, message, contract.getClient().getEmail());
        } catch (EmailException e) {
            return false;
        }
        return true;
    }

    private String getMessage(String template, String ... messages){
        StringBuilder builder = new StringBuilder(template);
        for(String str : messages){
            int start = builder.indexOf("?");
            builder.replace(start, start+1, str);
        }
        return builder.toString();
    }
}