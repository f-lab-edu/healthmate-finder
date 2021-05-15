package com.hamryt.helparty.exception.board.mateboard;

public class InsertMateBoardFailedException extends RuntimeException {
    
    public InsertMateBoardFailedException(String healthMateBoard) {
        super("Insert HealthMateBoard Failed Exception: " + healthMateBoard);
    }
    
}