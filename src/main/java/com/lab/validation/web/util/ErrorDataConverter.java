package com.lab.validation.web.util;

import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;

public class ErrorDataConverter {

    private ErrorDataConverter() {
    }

    public static <T> FieldError from(ConstraintViolation<T> violation) {
        final String code = violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
        final Object rejected = violation.getInvalidValue();
        final String field = violation.getPropertyPath().toString();
        final String message = violation.getMessage();
        final String objectName = violation.getRootBeanClass().getSimpleName().toLowerCase();

        return new FieldError(objectName, field, rejected, true, new String[]{code}, new Object[]{}, message);
    }

}
