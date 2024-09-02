package com.bootcamp.pragma.usermicroservice.domain.exception;

public class NoRoleFoundException extends RuntimeException {
    public NoRoleFoundException(final String message) {
        super(message);
    }
}
