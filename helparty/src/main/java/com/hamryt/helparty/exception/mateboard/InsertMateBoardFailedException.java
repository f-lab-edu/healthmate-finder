package com.hamryt.helparty.exception.mateboard;

public class InsertMateBoardFailedException extends RuntimeException{

    public InsertMateBoardFailedException(String healthMateBoard){
        super("Insert HealthMateBoard Failed Exception: " + healthMateBoard);
    }

}
