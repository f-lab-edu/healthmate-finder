package com.hamryt.helparty.dto.board.gymboard.request;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.board.product.ProductDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateGymBoardRequest {

    private long gymboardId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private List<Integer> deleteProductIdList;

    private List<ProductDTO> createProductList;

    private List<ProductDTO> updateProductList;

    @NotNull
    private UserType userType;

    @Builder
    public UpdateGymBoardRequest(
        long gymboardId, String title, String content,
        List<Integer> deleteProductIdList, List<ProductDTO> createProductList,
        List<ProductDTO> updateProductList, UserType userType
    ) {
        this.gymboardId = gymboardId;
        this.title = title;
        this.content = content;
        this.deleteProductIdList = deleteProductIdList;
        this.createProductList = createProductList;
        this.updateProductList = updateProductList;
        this.userType = userType;
    }

    public static UpdateGymBoardRequest of(long gymboardId, UpdateGymBoardRequest updateGymBoardRequest) {
        return UpdateGymBoardRequest.builder()
            .gymboardId(gymboardId)
            .title(updateGymBoardRequest.getTitle())
            .content(updateGymBoardRequest.getContent())
            .deleteProductIdList(updateGymBoardRequest.getDeleteProductIdList())
            .createProductList(updateGymBoardRequest.getCreateProductList())
            .updateProductList(updateGymBoardRequest.getUpdateProductList())
            .build();
    }

}
