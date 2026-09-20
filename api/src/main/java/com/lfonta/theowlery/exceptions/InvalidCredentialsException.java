package com.lfonta.theowlery.exceptions;

public class InvalidCredentialsException extends AuthenticationException {

    public InvalidCredentialsException() {
        super("INVALID_CREDENTIALS", "Invalid login credentials.");
    }

    public InvalidCredentialsException(Throwable e) {
        super("INVALID_CREDENTIALS", "Invalid login credentials.", e);
    }
}
