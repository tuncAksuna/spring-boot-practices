package com.interview.tuncode.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({SourceAlreadyExistsException.class})
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(SourceAlreadyExistsException exc) {
        HttpStatus status = HttpStatus.CONFLICT;

        ErrorResponse errResponse = new ErrorResponse(exc.getLocalizedMessage(),
                status.value(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(errResponse, status);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({SourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleSourceNotFoundException(SourceNotFoundException exc) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse errorResponse = new ErrorResponse(exc.getLocalizedMessage(),
                status.value(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(errorResponse, status);
    }

}
