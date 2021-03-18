package com.hamryt.helparty.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("This user does not exists");
    }
}
