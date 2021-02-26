package com.hamryt.helparty.exception;

public class UnexpectedResultException extends RuntimeException {

    public UnexpectedResultException() {
        super();
    }

    public UnexpectedResultException(String error) {
        super(error);
    }
}
