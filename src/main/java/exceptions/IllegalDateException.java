package exceptions;

public class IllegalDateException extends Exception {

    String message;

    public IllegalDateException() {
    }

    public IllegalDateException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
