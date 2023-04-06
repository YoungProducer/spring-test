package com.example.consumingrest;

public class EntityConflictException extends RuntimeException {

    public EntityConflictException(String field, String value) {
        super(EntityConflictException.generateMessage(field, value));
    }

    private static String generateMessage(String field, String value) {
        return field + " with value " + value + " is already taken.";
    }

}
