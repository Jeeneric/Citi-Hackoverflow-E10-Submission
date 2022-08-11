package com.example.Knowledge.exception;

public class ArticleAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ArticleAlreadyExistException() {
        super("Article already exists");
    }
}
