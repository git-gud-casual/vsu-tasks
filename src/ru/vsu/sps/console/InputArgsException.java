package ru.vsu.sps.console;

public class InputArgsException extends Exception {
    private String message = null;

    public InputArgsException() {
        super();
    }

    public InputArgsException(String message) {
        super(message);
        this.message = message;
    }

    public InputArgsException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
