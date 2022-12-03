package com.interview.tuncode.exceptions;


public class SourceAlreadyExistsException extends RuntimeException {

    public SourceAlreadyExistsException(String message) {
        super(message);
    }

    public SourceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
