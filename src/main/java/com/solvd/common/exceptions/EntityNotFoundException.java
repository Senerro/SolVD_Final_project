package com.solvd.common.exceptions;

public class EntityNotFoundException extends NavigationException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
