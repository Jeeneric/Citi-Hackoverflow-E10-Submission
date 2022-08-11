package com.jovan.msaccounts.exceptions;

public class InvalidJWTException extends RuntimeException{
    public InvalidJWTException(String message){
        super(message);
    }
}
