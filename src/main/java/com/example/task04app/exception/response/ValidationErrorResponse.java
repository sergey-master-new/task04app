/*
 * @author S.Maevsky
 * @since 03.03.2022, 17:09
 * @version V 1.0.0
 */

package com.example.task04app.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Validation error response.
 * Used to respond to the user interface when an ExceptionHandler handles some validation exceptions.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {

    private List<Violation> violations = new ArrayList<>();

}
