package org.example.superapiv1.exception;

public class UnexpectedIdException extends RuntimeException {
    public UnexpectedIdException(String entity) {
        super(entity);
    }
}


