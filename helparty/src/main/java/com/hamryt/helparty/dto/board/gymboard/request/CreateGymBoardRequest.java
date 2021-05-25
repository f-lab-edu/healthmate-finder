package com.hamryt.helparty.dto.board.gymboard.request;

import com.hamryt.helparty.dto.board.product.ProductDTO;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateGymBoardRequest {
    
    @NotEmpty
    private String title;
    
    @NotEmpty
    private String content;
    
    private List<ProductDTO> productList;
    
    @Builder
    public CreateGymBoardRequest(
        String title, String content, List<ProductDTO> productList
    ) {
        this.title = title;
        this.content = content;
        this.productList = productList;
    }
    
}
