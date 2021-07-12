package com.hamryt.helparty.dto.board.gymboard.response;

import com.hamryt.helparty.dto.board.gymboard.GymBoardDTO;
import com.hamryt.helparty.dto.board.product.ProductDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetGymBoardResponse {

    private Long id;
    private String gymName;
    private String gymAddress;
    private String content;
    private List<ProductDTO> productList;

    @Builder
    public GetGymBoardResponse(
        Long id, String gymName, String gymAddress,
        String content, List<ProductDTO> productList
    ) {
        this.id = id;
        this.gymName = gymName;
        this.gymAddress = gymAddress;
        this.content = content;
        this.productList = productList;
    }

    public static GetGymBoardResponse of(GymBoardDTO gymBoardDTO) {
        return GetGymBoardResponse.builder()
            .id(gymBoardDTO.getId())
            .gymName(gymBoardDTO.getGymInfo().getGymName())
            .gymAddress(gymBoardDTO.getGymInfo().getAddressDetail())
            .content(gymBoardDTO.getContent())
            .productList(gymBoardDTO.getProductList())
            .build();
    }

}
