package com.hamryt.helparty.dto.board.product.request;

import com.hamryt.helparty.dto.board.product.ProductDTO.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SimpleProduct {
    
    private Long id;
    
    private String title;
    
    private String content;
    
    private String price;
    
    private String scope;
    
    private BoardType boardType;
    
    @Builder
    public SimpleProduct(
        String title, String content, String price,
        String scope, BoardType boardType
    ) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.scope = scope;
        this.boardType = boardType;
    }
}
