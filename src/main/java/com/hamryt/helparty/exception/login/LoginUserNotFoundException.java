package com.hamryt.helparty.exception.login;

public class LoginUserNotFoundException extends RuntimeException {

    public LoginUserNotFoundException() {
        super("Login User does not exists in Session.");
    }

}
