package com.hamryt.helparty.exception.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String user) {
        super("This user does not exists. " + user);
    }
}
