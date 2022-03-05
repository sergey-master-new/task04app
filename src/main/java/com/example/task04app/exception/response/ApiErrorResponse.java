/*
 * @author S.Maevsky
 * @since 03.03.2022, 17:02
 * @version V 1.0.0
 */

package com.example.task04app.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Api error response.
 * Used to respond to the user interface when an ExceptionHandler handles an exception.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {

    private String message;
}
