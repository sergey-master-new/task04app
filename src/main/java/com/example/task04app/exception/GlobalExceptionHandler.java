package com.example.task04app.exception;

import com.example.task04app.exception.response.ApiErrorResponse;
import com.example.task04app.exception.response.IncorrectDataResponse;
import com.example.task04app.exception.response.ValidationErrorResponse;
import com.example.task04app.exception.response.Violation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ValidationErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        ValidationErrorResponse errorResponse = new ValidationErrorResponse();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            errorResponse.getViolations().add(
                    new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        log.info("Exception when checking validation parameters", ex);

        return errorResponse;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse handleConstraintValidationException(
            ConstraintViolationException ex) {

        ValidationErrorResponse errorResponse = new ValidationErrorResponse();

        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            errorResponse.getViolations().add(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }

        log.info("Exception when checking validation parameters", ex);

        return errorResponse;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<IncorrectDataResponse> handleException(
            NotFoundException ex) {

        IncorrectDataResponse incorrectDataResponse = new IncorrectDataResponse(ex.getMessage());
        log.info("NotFoundException: ", ex);

        return new ResponseEntity<>(incorrectDataResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataBaseSQLException.class)
    public ResponseEntity<ApiErrorResponse> handleException(
            DataBaseSQLException ex) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getMessage());
        log.info("DataBaseSQLException: ", ex);

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExternalRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleException(
            ExternalRequestException ex) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getMessage());
        log.info("ExternalRequestException: ", ex);

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }
}
