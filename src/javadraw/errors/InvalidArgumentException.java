package javadraw.errors;

public class InvalidArgumentException extends Exception {

    private String message;

    public InvalidArgumentException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
