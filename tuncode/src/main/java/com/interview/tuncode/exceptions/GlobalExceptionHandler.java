package com.interview.tuncode.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({SourceAlreadyExistsException.class})
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(SourceAlreadyExistsException exc) {
        HttpStatus status = HttpStatus.CONFLICT;

        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(exc.getMessage())
                        .http_STATUS(status.value())
                        .localDateTime(LocalDateTime.now())
                        .stackTraceElement(Arrays.stream(exc.getStackTrace())
                                .findFirst())
                        .build(), status);
    }

    @ExceptionHandler({SourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleSourceNotFoundException(SourceNotFoundException exc) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(exc.getMessage())
                        .http_STATUS(status.value())
                        .localDateTime(LocalDateTime.now())
                        .stackTraceElement(Arrays.stream(exc.getStackTrace())
                                .findFirst())
                        .build(), status);
    }

}
