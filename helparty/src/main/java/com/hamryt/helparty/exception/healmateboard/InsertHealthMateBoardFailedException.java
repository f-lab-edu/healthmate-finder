package com.hamryt.helparty.exception.healmateboard;

public class InsertHealthMateBoardFailedException extends RuntimeException{

    public InsertHealthMateBoardFailedException(String healthMateBoard){
        super("Insert HealthMateBoard Failed Exception: " + healthMateBoard);
    }

}
