package com.hamryt.helparty.dto.board.product;

import com.hamryt.helparty.exception.board.BoardTypeDoesNotMatchException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDTO {
    
    public enum BoardType {
        GYM, PT
    }
    
    private Long id;

    private String title;

    private String content;

    private String price;

    private String scope;

    private BoardType boardType;
    
    @Builder
    public ProductDTO(Long id,
        String title, String content, String price,
        String scope, BoardType boardType
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
        this.scope = scope;
        this.boardType = boardType;
    }
    
    public static void checkBoardType(BoardType boardType, BoardType authType){
        if(!boardType.equals(authType)){
            throw new BoardTypeDoesNotMatchException(authType);
        }
    }
}
