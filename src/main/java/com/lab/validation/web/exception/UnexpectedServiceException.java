package com.lab.validation.web.exception;

public class UnexpectedServiceException extends RuntimeException {

    public UnexpectedServiceException(String message) {
        super(message);
    }

    public UnexpectedServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
