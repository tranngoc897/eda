package com.example.match.exception;

public class InvalidContentExeption extends RuntimeException {

    public InvalidContentExeption(String msg) {
        super(msg);
    }

    public InvalidContentExeption(String msg, Object itemId) {
        super(msg + (itemId == null ? "" : ": " + itemId));
    }
}
