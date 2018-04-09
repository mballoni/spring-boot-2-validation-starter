package com.lab.validation.web.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Parameters")
public class ValidationException extends RuntimeException {

    private final List<FieldError> errors;

    public ValidationException(final List<FieldError> fieldsErrors) {
        this(null, null, fieldsErrors);
    }

    public ValidationException(String message, final List<FieldError> fieldsErrors) {
        this(message, null, fieldsErrors);
    }

    public ValidationException(String message, Throwable cause, final List<FieldError> fieldsErrors) {
        super(message, cause);
        this.errors = Collections.unmodifiableList(fieldsErrors);
    }

    @Override
    public String toString() {
        return StringUtils.join(errors, StringUtils.LF);
    }

    public List<FieldError> getErrors() {
        return errors;
    }
}
