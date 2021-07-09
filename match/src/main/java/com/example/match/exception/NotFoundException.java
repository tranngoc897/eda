package com.example.match.exception;

public class NotFoundException extends RuntimeException {

    private Object id;

    public NotFoundException(String message, Object id) {
        super(message + ": " + id);
        this.id = id;
    }

    public Object getId() {
        return id;
    }
}
