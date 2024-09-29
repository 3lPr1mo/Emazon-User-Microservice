package com.bootcamp.pragma.usermicroservice.domain.exception;

public class UserIsUnderAgeException extends RuntimeException {
    public UserIsUnderAgeException(String message) {
        super(message);
    }
}
