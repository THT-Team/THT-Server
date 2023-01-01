package com.tht.api.app.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class ThtException extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public ThtException(String message) {
        super(message);
    }

    public ThtException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
