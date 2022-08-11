package com.example.MSIAM.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException() {
        super("Username already in use");
    }

}
