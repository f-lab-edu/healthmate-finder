package com.hamryt.helparty.exception.board.gymboard;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GymBoardNotFoundException extends ResponseStatusException {
    
    public GymBoardNotFoundException() {
        this("Not found Gymboard. ");
    }
    
    public GymBoardNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
