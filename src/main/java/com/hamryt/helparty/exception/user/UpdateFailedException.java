package com.hamryt.helparty.exception.user;

public class UpdateFailedException extends RuntimeException{
    public UpdateFailedException(Long id){
        super("Update did not happened with this id : " + id);
    }
}
