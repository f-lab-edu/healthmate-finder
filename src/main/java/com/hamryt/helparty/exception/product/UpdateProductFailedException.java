package com.hamryt.helparty.exception.product;

public class UpdateProductFailedException extends RuntimeException {

    public UpdateProductFailedException() {
        super("Update Product failed exception");
    }

    public UpdateProductFailedException(String message) {
        super(message);
    }
}
