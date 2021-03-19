package com.hamryt.helparty.exception.user;

public class UserNotFoundByEmailException extends RuntimeException{

    public UserNotFoundByEmailException(String email){
        super("User not found by Email : " + email);
    }

}
