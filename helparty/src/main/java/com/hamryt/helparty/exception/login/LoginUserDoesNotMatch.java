package com.hamryt.helparty.exception.login;

public class LoginUserDoesNotMatch extends RuntimeException {

    public LoginUserDoesNotMatch(String email) {
        super("Login user dose not match with : " + email);
    }
}
