package com.pruebatecnica.tareasservices.execption;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiExecption {

    private final HttpStatus httpStatus;
    private final String message;
    private final ZonedDateTime timestamp;



    public ApiExecption(String message,
                        HttpStatus httpStatus,
                        ZonedDateTime timestamp) {

        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = timestamp;

    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
