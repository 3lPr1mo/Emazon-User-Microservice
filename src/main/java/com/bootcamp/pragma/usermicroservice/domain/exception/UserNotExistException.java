package com.bootcamp.pragma.usermicroservice.domain.exception;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(String message) {
        super(message);
    }
}
