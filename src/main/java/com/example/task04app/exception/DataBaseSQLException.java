package com.example.task04app.exception;

public class DataBaseSQLException extends RuntimeException {

    public DataBaseSQLException() {
        super();
    }

    public DataBaseSQLException(String message) {
        super(message);
    }

    public DataBaseSQLException(String message, Throwable cause) {
        super(message, cause);
    }
}
