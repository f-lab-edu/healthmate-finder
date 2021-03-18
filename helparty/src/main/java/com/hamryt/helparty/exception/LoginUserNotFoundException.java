package com.hamryt.helparty.exception;

public class LoginUserNotFoundException extends RuntimeException {

    public LoginUserNotFoundException() {
        super("Login User does not exists in Session.");
    }

}
