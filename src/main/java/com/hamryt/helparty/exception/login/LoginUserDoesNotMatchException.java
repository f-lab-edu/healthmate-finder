package com.hamryt.helparty.exception.login;

public class LoginUserDoesNotMatchException extends RuntimeException {

    public LoginUserDoesNotMatchException(Long id) {
        super("Login user dose not match with : " + id);
    }
}
