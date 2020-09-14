package am.tech42.spring.exception;

public class UnknownUserException extends Exception {

    public UnknownUserException(String message) {
        super(message);
    }
}
