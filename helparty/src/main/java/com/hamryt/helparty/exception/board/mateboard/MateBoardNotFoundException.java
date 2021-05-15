package com.hamryt.helparty.exception.board.mateboard;

public class MateBoardNotFoundException extends RuntimeException {
    
    public MateBoardNotFoundException(Long id) {
        super("MateBoard Not Found Exception with id: " + id);
    }
}
