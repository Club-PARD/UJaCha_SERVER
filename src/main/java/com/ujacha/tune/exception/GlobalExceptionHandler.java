package com.ujacha.tune.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("NullPointerException occurred: " + ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("IllegalStateException occurred: " + ex.getMessage());
    }
    @ExceptionHandler({JwtException.class})
    public ResponseEntity<String> handleJwtSignatureException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such element exception occurred: " + ex.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleTestOnceInDayO() {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("하루에 한번만 가능해요");

    }

}