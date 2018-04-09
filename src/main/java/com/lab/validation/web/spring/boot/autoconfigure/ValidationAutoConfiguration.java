package com.lab.validation.web.spring.boot.autoconfigure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lab.validation.web.exception.ValidationException;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@ComponentScan("com.lab.validation.web")
public class ValidationAutoConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer addFieldErrorMixIn() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.mixIn(FieldError.class, FieldErrorMixIn.class);
    }

    public interface FieldErrorMixIn {
        @JsonIgnore
        String[] getCodes();

        @JsonIgnore
        String getObjectName();

        @JsonIgnore
        Object[] getArguments();

        @JsonIgnore
        boolean isBindingFailure();

        @JsonProperty("message")
        String getDefaultMessage();
    }

    @Bean
    @Primary
    public DefaultErrorAttributes defaultErrorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest request, boolean includeStackTrace) {

                Map<String, Object> errorAttributes = super.getErrorAttributes(request, includeStackTrace);
                errorAttributes.remove("exception");

                Throwable exception = super.getError(request);
                if (exception instanceof ValidationException) {
                    ValidationException validationException = (ValidationException) exception;
                    if (!validationException.getErrors().isEmpty()) {
                        errorAttributes.put("errors", validationException.getErrors());
                    }
                }

                return errorAttributes;
            }

        };
    }
}
