package com.lfonta.littleowl.exceptions;

public class UserNotFound extends ApiException {
    public UserNotFound() {
        super(400, "user_not_found", "The user does not have an previous existing link with the app.");
    }
}
