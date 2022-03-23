/*
 * @author S.Maevsky
 * @since 03.03.2022, 17:14
 * @version V 1.0.0
 */

package com.example.task04app.exception;

/**
 * The type External response exception.
 */
public class ExternalResponseException extends RuntimeException {

    /**
     * Instantiates a new External response exception.
     */
    public ExternalResponseException() {
        super();
    }

    /**
     * Instantiates a new External response exception.
     *
     * @param message the String message
     */
    public ExternalResponseException(String message) {
        super(message);
    }

    /**
     * Instantiates a new External response exception.
     *
     * @param message the String message
     * @param cause   the Throwable cause
     */
    public ExternalResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
