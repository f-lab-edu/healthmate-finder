package com.hamryt.helparty.dto.board.product;

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
    
    private Long gymBoardId;
    
    @Builder
    public ProductDTO(Long id,
        String title, String content, String price,
        String scope, BoardType boardType, Long gymBoardId
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
        this.scope = scope;
        this.boardType = boardType;
        this.gymBoardId = gymBoardId;
    }
}
