package com.lfonta.littleowl.exceptions;

public class InvalidOAuthCredentialsException extends AuthenticationException {

    public InvalidOAuthCredentialsException() {
        super("INVALID_OAUTH_CREDENTIALS", "Invalid OAuth credentials.");
    }

    public InvalidOAuthCredentialsException(Throwable e) {
        super("INVALID_OAUTH_CREDENTIALS", "Invalid OAuth credentials.", e);
    }
}
