package com.bootcamp.pragma.usermicroservice.infrastructure.driving.http.controller.exceptionhandler;

import com.bootcamp.pragma.usermicroservice.domain.exception.PasswordDoNotMatchException;
import com.bootcamp.pragma.usermicroservice.domain.exception.RoleNotFoundException;
import com.bootcamp.pragma.usermicroservice.domain.exception.UserAlreadyExistException;
import com.bootcamp.pragma.usermicroservice.domain.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(PasswordDoNotMatchException.class)
    public ResponseEntity<ExceptionResponse> handlePasswordDoNotMatchException(final PasswordDoNotMatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleRoleNotFoundException(final RoleNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistException(final UserAlreadyExistException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotExistException(final UserNotExistException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        ));
    }
}
