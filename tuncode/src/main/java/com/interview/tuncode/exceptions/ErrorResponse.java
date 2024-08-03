package com.interview.tuncode.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private final String message;
    private final int http_STATUS;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime localDateTime;
    private final Optional<StackTraceElement> stackTraceElement;

}
