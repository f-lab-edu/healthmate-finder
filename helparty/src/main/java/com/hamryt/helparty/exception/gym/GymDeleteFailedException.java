package com.hamryt.helparty.exception.gym;

public class GymDeleteFailedException extends RuntimeException {
    
    public GymDeleteFailedException(Long id) {
        super("Delete Gym by ID Failed : " + id);
    }
}
