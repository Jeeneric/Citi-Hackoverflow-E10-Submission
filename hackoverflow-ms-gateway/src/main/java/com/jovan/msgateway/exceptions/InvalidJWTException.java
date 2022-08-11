package com.jovan.msgateway.exceptions;

public class InvalidJWTException extends RuntimeException{
    public InvalidJWTException(String message){
        super(message);
    }
}
