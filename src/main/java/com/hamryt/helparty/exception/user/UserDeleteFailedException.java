package com.hamryt.helparty.exception.user;

public class UserDeleteFailedException extends RuntimeException {
    
    public UserDeleteFailedException(Long id) {
        super("Delete User By Id Failed : " + id);
    }
}
