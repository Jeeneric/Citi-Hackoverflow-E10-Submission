package com.jovan.msaccounts.configs;

import com.jovan.msaccounts.controllers.responses.ExceptionResponse;
import com.jovan.msaccounts.exceptions.AccountNotFoundException;
import com.jovan.msaccounts.exceptions.InvalidJWTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleAccountNotFoundException(AccountNotFoundException e){
        log.info("caught AccountNotFoundException");
        return ExceptionResponse.builder()
                .errorDetail(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = InvalidJWTException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ExceptionResponse handleInvalidJWTException(InvalidJWTException e){
        log.info("caught InvalidJWTException");
        return ExceptionResponse.builder()
                .errorDetail(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
        log.info("caught MissingServletRequestParameterException");
        return ExceptionResponse.builder()
                .errorDetail(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.info("caught MethodArgumentNotValidException");
        return ExceptionResponse.builder()
                .errorDetail("received request is invalid / has missing fields")
                .build();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionResponse handleUncaughtException(Exception e){
        log.info("caught unexpected exception: {}", e);
        return ExceptionResponse.builder()
                .errorDetail("unexpected error occurred")
                .build();
    }

}
