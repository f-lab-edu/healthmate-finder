package com.hamryt.helparty.exception.product;

public class InsertProductFailedException extends RuntimeException {
    
    public InsertProductFailedException() {
        super("Insert Product failed exception");
    }
    
    public InsertProductFailedException(String message) {
        super(message);
    }
    
}
