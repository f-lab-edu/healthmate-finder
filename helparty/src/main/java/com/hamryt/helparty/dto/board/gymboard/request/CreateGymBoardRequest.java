package com.hamryt.helparty.dto.board.gymboard.request;

import com.hamryt.helparty.dto.UserType;
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
    private UserType userType;
    
    @Builder
    public CreateGymBoardRequest(
        String title, String content, SimpleProduct simpleProduct, UserType userType
    ) {
        this.title = title;
        this.content = content;
        this.simpleProduct = simpleProduct;
        this.userType = userType;
    }
    
}
