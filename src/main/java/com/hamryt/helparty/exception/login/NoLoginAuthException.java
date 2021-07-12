package com.hamryt.helparty.exception.login;

public class NoLoginAuthException extends RuntimeException {

    public NoLoginAuthException() {
        super("No login Authority");
    }
}
