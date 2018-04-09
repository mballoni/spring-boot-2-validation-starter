package com.lab.validation.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    private final String identifier;

    public EntityNotFoundException(final String identifier, final String message) {
        super(message);
        this.identifier = identifier;
    }

    public EntityNotFoundException(final String identifier) {
        this(identifier, null);
    }

    public String getIdentifier() {
        return identifier;
    }

}
