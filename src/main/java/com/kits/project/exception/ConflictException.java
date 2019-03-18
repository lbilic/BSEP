package com.kits.project.exception;

public class ConflictException extends RuntimeException {

    public ConflictException() { }

    public ConflictException(String message) {
        super(message);
    }
}