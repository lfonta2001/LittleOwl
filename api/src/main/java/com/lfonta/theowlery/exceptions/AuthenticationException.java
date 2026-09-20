package com.lfonta.theowlery.exceptions;

public class AuthenticationException extends ApiException {
    public AuthenticationException(String name, String message, Throwable e) {
        super(401, name, message, e);
    }

    public AuthenticationException(String name, String message) {
        this(name, message, null);
    }

    public AuthenticationException(Throwable e) {
        this("AUTHENTICATION_ERROR", "Unknown reason.", e);
    }

    public AuthenticationException() {
        this(null);
    }
}
