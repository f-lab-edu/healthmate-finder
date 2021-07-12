package com.hamryt.helparty.exception.gym;

public class GymNotFoundException extends RuntimeException {
    
    public GymNotFoundException(String email) {
        super("This gym does not exists with this email : " + email);
    }
    
}
