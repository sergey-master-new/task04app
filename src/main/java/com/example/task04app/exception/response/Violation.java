package com.example.task04app.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Violation {

    private final String fieldName;

    private final String message;

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

}
