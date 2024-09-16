package com.bootcamp.pragma.usermicroservice.domain.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(final String message) {
        super(message);
    }
}
