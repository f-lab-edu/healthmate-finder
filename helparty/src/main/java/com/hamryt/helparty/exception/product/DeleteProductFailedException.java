package com.hamryt.helparty.exception.product;

public class DeleteProductFailedException extends RuntimeException{

    public DeleteProductFailedException(){super("Delete Product Failed exception");}

    public DeleteProductFailedException(String message){super(message);}

}
