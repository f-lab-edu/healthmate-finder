package com.hamryt.helparty.exception;

public class UnexpectedInsertException extends RuntimeException {

  public UnexpectedInsertException() {
    super();
  }

  public UnexpectedInsertException(String error) {
    super(error);
  }
}
