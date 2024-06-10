package com.nexusforge.springgraphqlplayground.lec15.exceptions;

import lombok.Getter;
import org.springframework.graphql.execution.ErrorType;

import java.util.Map;

public class ApplicationException extends RuntimeException{

    @Getter
    private final ErrorType errorType;
    private final String message;
    @Getter
    private final Map<String, Object> extensions;

    public ApplicationException(String message,  ErrorType errorType, Map<String, Object> extensions) {
        super(message);
        this.errorType = errorType;
        this.message = message;
        this.extensions = extensions;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
