package com.education.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends RuntimeException {
    private HttpStatus status;

    public InvalidTokenException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public InvalidTokenException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
