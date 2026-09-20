package com.lfonta.theowlery.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    @Getter
    private final HttpStatus status;
    @Getter
    private final String name;

    public ApiException(int status, String name, String message, Throwable e) {
        super(message, e);
        this.name= name;
        this.status = HttpStatus.resolve(status);
    }

    public ApiException(int status, String name, String message) {
        this(status, name, message, null);
    }
}
