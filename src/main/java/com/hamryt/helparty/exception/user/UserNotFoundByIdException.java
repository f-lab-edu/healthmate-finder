package com.hamryt.helparty.exception.user;

public class UserNotFoundByIdException extends RuntimeException{

    public  UserNotFoundByIdException(Long id){
        super("User not Found with This id : " + id);
    }

}
