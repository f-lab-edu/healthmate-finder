package com.hamryt.helparty.dto.board.gymboard.request;

import com.hamryt.helparty.dto.board.product.request.SimpleProduct;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    
    @NotNull
    private SimpleProduct simpleProduct;
    
    @NotNull
    private Long gymId;
    
    @Builder
    public CreateGymBoardRequest(
        String title, String content, SimpleProduct simpleProduct, Long gymId
    ) {
        this.title = title;
        this.content = content;
        this.simpleProduct = simpleProduct;
        this.gymId = gymId;
    }
    
}
