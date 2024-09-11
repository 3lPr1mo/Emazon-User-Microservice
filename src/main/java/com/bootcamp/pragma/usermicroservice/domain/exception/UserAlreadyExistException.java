package com.bootcamp.pragma.usermicroservice.domain.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(final String message) {
        super(message);
    }
}
