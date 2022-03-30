package com.education.exceptions;


public class BadRequestException extends RuntimeException{
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
