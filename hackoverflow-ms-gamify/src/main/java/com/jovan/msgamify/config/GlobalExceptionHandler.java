package com.jovan.msgamify.config;

import com.jovan.msgamify.controllers.responses.ExceptionResponse;
import com.jovan.msgamify.exceptions.BadRequestException;
import com.jovan.msgamify.exceptions.EntityNotFoundException;
import com.jovan.msgamify.exceptions.InvalidJWTException;
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

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionResponse handleEntityNotFoundException(EntityNotFoundException e){
        log.info("caught EntityNotFoundException");
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

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleBadRequestException(BadRequestException e){
        log.info("caught BadRequestException");
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
