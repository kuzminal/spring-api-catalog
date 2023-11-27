package ru.kuzmin.apicatalog.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kuzmin.apicatalog.domain.dto.validation.ValidationErrorDTO;
import ru.kuzmin.apicatalog.domain.dto.validation.ViolationDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorDTO onConstraintValidationException(
            ConstraintViolationException e) {
        ValidationErrorDTO error = new ValidationErrorDTO(new ArrayList<>());
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.violations().add(
                    new ViolationDTO(violation.getPropertyPath().toString(),
                            violation.getMessage()));
        }
        return error;
    }
}
