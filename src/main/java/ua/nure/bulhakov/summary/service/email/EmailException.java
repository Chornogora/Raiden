package ua.nure.bulhakov.summary.service.email;

/**
 * Exception that is thrown out of packages
 * @author A.Bulhakov
 */
public class EmailException extends Exception {
    public EmailException(String message, Throwable cause){
        super(message, cause);
    }
}
