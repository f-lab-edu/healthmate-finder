package com.hamryt.helparty.exception.gym;

public class GymNotFoundByIdException extends RuntimeException {
    
    public GymNotFoundByIdException(Long id) {
        super("This gym does not exists with this id : " + id);
    }
    
}
