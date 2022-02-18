package com.example.task04app.exception;

public class ExternalRequestException extends RuntimeException {

    public ExternalRequestException() {
        super();
    }

    public ExternalRequestException(String message) {
        super(message);
    }

    public ExternalRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
