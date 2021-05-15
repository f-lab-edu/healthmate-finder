package com.hamryt.helparty.exception.user;

public class UpdateFailedException extends RuntimeException{
    public UpdateFailedException(String user){
        super("Update did not happened with this model : " + user);
    }
}
