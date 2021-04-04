package com.hamryt.helparty.exception.user;

public class InsertUserFailedException extends RuntimeException{
    public InsertUserFailedException(String user){
        super("Insert User failed exception : " + user);
    }
}
