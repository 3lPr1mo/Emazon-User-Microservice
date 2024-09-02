package com.bootcamp.pragma.usermicroservice.domain.exception;

public class RoleAlreadyExistsException extends RuntimeException {
    public RoleAlreadyExistsException(final String message) {
        super(message);
    }
}
