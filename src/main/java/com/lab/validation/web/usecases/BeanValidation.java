package com.lab.validation.web.usecases;

import com.lab.validation.web.exception.ValidationException;
import com.lab.validation.web.util.ErrorDataConverter;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Component
public class BeanValidation implements Validator {

    private final javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public <T> void validate(final T entity) {
        final Set<ConstraintViolation<T>> violations = validator.validate(entity);
        check(violations);
    }

    @Override
    public <T> void validate(final T entity, Class<?>... groups) {
        final Set<ConstraintViolation<T>> violations = validator.validate(entity, groups);
        check(violations);
    }

    private <T> void check(final Set<ConstraintViolation<T>> violations) {
        if (violations.isEmpty()) {
            return;
        }
        List<FieldError> errors = violations.stream()
                .map(ErrorDataConverter::from)
                .collect(toList());
        throw new ValidationException(errors);
    }
}