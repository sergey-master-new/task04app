/*
 * @author S.Maevsky
 * @since 03.03.2022, 17:14
 * @version V 1.0.0
 */

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

/**
 * The type Global exception handler.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handle method argument not valid exception.
     *
     * @param ex the MethodArgumentNotValidException ex
     * @return the validation error response
     */
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

        log.debug("Exception when checking validation parameters", ex);

        return errorResponse;
    }

    /**
     * Handle constraint validation exception.
     *
     * @param ex the ConstraintViolationException ex
     * @return the validation error response
     */
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

        log.debug("Exception when checking validation parameters", ex);

        return errorResponse;
    }

    /**
     * Handle not found exception.
     *
     * @param ex the NotFoundException ex
     * @return the response entity with the incorrect data response
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<IncorrectDataResponse> handleException(
            NotFoundException ex) {

        IncorrectDataResponse incorrectDataResponse = new IncorrectDataResponse(ex.getMessage());
        log.debug("NotFoundException: ", ex);

        return new ResponseEntity<>(incorrectDataResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle database access exception.
     *
     * @param ex the DataBaseAccessException ex
     * @return the response entity with api error response
     */
    @ExceptionHandler(DataBaseAccessException.class)
    public ResponseEntity<ApiErrorResponse> handleException(
            DataBaseAccessException ex) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getMessage());
        log.debug("DataBaseAccessException: ", ex);

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle exception request entity.
     *
     * @param ex the ExternalRequestException ex
     * @return the response entity with api error response
     */
    @ExceptionHandler(ExternalRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleException(
            ExternalRequestException ex) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getMessage());
        log.debug("ExternalRequestException: ", ex);

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle external response exception.
     *
     * @param ex the ExternalResponseException ex
     * @return the response entity with api error response
     */
    @ExceptionHandler(ExternalResponseException.class)
    public ResponseEntity<ApiErrorResponse> handleException(
            ExternalResponseException ex) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getMessage());
        log.debug("ExternalResponseException: ", ex);

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }
}
