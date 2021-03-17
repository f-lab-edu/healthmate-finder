package com.hamryt.helparty.exception;

public class UserNotExistedException extends RuntimeException {

    public UserNotExistedException(String email) {
        super("This email user does not exists: " + email);
    }
}
