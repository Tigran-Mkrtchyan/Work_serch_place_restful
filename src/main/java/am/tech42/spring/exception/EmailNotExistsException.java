package am.tech42.spring.exception;

public class EmailNotExistsException extends Exception {
    public EmailNotExistsException(String message) {
        super(message);
    }
}
