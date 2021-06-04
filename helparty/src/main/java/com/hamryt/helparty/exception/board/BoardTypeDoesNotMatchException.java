package com.hamryt.helparty.exception.board;

import com.hamryt.helparty.dto.board.product.ProductDTO.BoardType;

public class BoardTypeDoesNotMatchException extends RuntimeException {
    
    public BoardTypeDoesNotMatchException(BoardType boardType) {
        super("BoardType does not match with : " + boardType);
    }
}
