package com.example.MSIAM.exceptions;

public class TokenAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TokenAlreadyExistsException() {
        super("Token already exists");
    }

}
