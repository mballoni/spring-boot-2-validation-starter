package com.lab.validation.web.usecases;

public interface Validator {
    <T> void validate(T entity);

    <T> void validate(T entity, Class<?>... groups);
}
