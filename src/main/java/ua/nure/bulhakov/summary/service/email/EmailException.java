package ua.nure.bulhakov.summary.service.email;

public class EmailException extends Exception {
    public EmailException(String message, Throwable cause){
        super(message, cause);
    }
}
