package com.hamryt.helparty.exception.user;

public class UserDeleteFailedException extends RuntimeException{
    public UserDeleteFailedException(String email){
        super("Delete User By email Failed : " + email);
    }
}
