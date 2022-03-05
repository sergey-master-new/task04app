/*
 * @author S.Maevsky
 * @since 03.03.2022, 17:13
 * @version V 1.0.0
 */

package com.example.task04app.exception;

/**
 * The type External request exception.
 */
public class ExternalRequestException extends RuntimeException {

    /**
     * Instantiates a new External request exception.
     */
    public ExternalRequestException() {
        super();
    }

    /**
     * Instantiates a new External request exception.
     *
     * @param message the String message
     */
    public ExternalRequestException(String message) {
        super(message);
    }

    /**
     * Instantiates a new External request exception.
     *
     * @param message the String message
     * @param cause   the Throwable cause
     */
    public ExternalRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
