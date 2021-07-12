package com.hamryt.helparty.dto.board.gymboard.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hamryt.helparty.dto.board.gymboard.request.UpdateGymBoardRequest;
import com.hamryt.helparty.dto.board.product.ProductDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateGymBoardResponse {

    private Long id;

    private String title;

    private String content;

    private String gymName;

    private String gymAddress;

    private List<ProductDTO> productList;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;

    @Builder
    public UpdateGymBoardResponse(
        Long id, String title, String gymName, String gymAddress,
        String content, List<ProductDTO> productList,
        LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.title = title;
        this.gymName = gymName;
        this.gymAddress = gymAddress;
        this.content = content;
        this.productList = productList;
        this.modifiedAt = modifiedAt;
    }

    public static UpdateGymBoardResponse of(UpdateGymBoardRequest updateGymBoardRequest, Long gymBoardId) {
        return UpdateGymBoardResponse.builder()
            .id(gymBoardId)
            .title(updateGymBoardRequest.getTitle())
            .content(updateGymBoardRequest.getContent())
            .productList(updateGymBoardRequest.getUpdateProductList())
            .build();
    }

}
