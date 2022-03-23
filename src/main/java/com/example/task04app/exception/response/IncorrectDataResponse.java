/*
 * @author S.Maevsky
 * @since 03.03.2022, 17:06
 * @version V 1.0.0
 */

package com.example.task04app.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The type Incorrect data response.
 * Used ExceptionHandler in the process of preparing a response when handling an exception.
 */
@Data
@AllArgsConstructor
public class IncorrectDataResponse {

    private String message;

}

