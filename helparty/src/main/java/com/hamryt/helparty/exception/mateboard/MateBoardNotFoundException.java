package com.hamryt.helparty.exception.mateboard;

public class MateBoardNotFoundException extends RuntimeException{
    public MateBoardNotFoundException(Long id){
        super("MateBoard Not Found Excetpion with id: "+ id);
    }
}
