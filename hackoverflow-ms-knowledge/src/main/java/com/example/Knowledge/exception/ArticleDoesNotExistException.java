package com.example.Knowledge.exception;

public class ArticleDoesNotExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ArticleDoesNotExistException() {
        super("Article does not exist");
    }
}
