package com.interview.tuncode.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public class ErrorResponse {

    private final boolean studentUpdatedBefore;
    private final String message;
    private final int http_STATUS;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final ZonedDateTime zonedDateTime;


    public ErrorResponse(boolean studentUpdatedBefore, String message, int http_STATUS, ZonedDateTime zonedDateTime) {
        this.studentUpdatedBefore = studentUpdatedBefore;
        this.message = message;
        this.http_STATUS = http_STATUS;
        this.zonedDateTime = zonedDateTime;
    }

    public boolean isStudentUpdatedBefore() {
        return studentUpdatedBefore;
    }

    public String getMessage() {
        return message;
    }

    public int getHttp_STATUS() {
        return http_STATUS;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
