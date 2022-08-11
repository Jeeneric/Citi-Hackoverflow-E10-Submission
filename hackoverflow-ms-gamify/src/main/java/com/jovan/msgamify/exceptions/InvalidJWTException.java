package com.jovan.msgamify.exceptions;

public class InvalidJWTException extends RuntimeException{
    public InvalidJWTException(String message){
        super(message);
    }
}
