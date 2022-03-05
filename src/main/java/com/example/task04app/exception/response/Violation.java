/*
 * @author S.Maevsky
 * @since 03.03.2022, 17:10
 * @version V 1.0.0
 */

package com.example.task04app.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The type Violation.
 * Used by class ValidationErrorResponse.
 */
@Data
@AllArgsConstructor
public class Violation {

    private final String fieldName;

    private final String message;

    /**
     * Gets field name.
     *
     * @return the field name
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

}
