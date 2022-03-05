/*
 * @author S.Maevsky
 * @since 03.03.2022, 17:11
 * @version V 1.0.0
 */

package com.example.task04app.exception;

/**
 * The type Data base access exception.
 */
public class DataBaseAccessException extends RuntimeException {

    /**
     * Instantiates a new Database access exception.
     */
    public DataBaseAccessException() {
        super();
    }

    /**
     * Instantiates a new Database access exception.
     *
     * @param message the String message
     */
    public DataBaseAccessException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Database access exception.
     *
     * @param message the String message
     * @param cause   the Throwable cause
     */
    public DataBaseAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
