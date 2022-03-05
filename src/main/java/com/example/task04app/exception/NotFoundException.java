/*
 * @author S.Maevsky
 * @since 04.03.2022, 0:19
 * @version V 1.0.0
 */

package com.example.task04app.exception;

/**
 * The type Not found exception.
 */
public class NotFoundException extends RuntimeException{

    /**
     * Instantiates a new Not found exception.
     *
     * @param message the  String message
     */
    public NotFoundException(String message) {
        super(message);
    }
}
