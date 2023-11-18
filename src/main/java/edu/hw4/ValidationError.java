package edu.hw4;

public class ValidationError extends Error {

    private final String fieldName;

    public ValidationError(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
