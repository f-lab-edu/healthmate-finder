package com.hamryt.helparty.exception.login;

public class LoginUserDoesNotMatchException extends RuntimeException {

    public LoginUserDoesNotMatchException(String email) {
        super("Login user dose not match with : " + email);
    }
}
