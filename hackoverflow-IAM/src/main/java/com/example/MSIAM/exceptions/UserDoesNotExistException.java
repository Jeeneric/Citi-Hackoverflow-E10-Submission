package com.example.MSIAM.exceptions;

public class UserDoesNotExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserDoesNotExistException() {
        super("User does not exists");
    }

}
