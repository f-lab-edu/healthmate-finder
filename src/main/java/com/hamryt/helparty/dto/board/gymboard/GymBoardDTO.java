package com.hamryt.helparty.dto.board.gymboard;

import com.hamryt.helparty.dto.board.product.ProductDTO;
import com.hamryt.helparty.dto.gym.SimpleGymInfo;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GymBoardDTO {
    
    private Long id;
    
    private String title;
    
    private String content;
    
    private List<ProductDTO> productList;
    
    private SimpleGymInfo gymInfo;
    
    private Long gymId;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime modifiedAt;
    
    @Builder
    public GymBoardDTO(
        Long id, String title, String content, Long gymId,
        List<ProductDTO> productList, SimpleGymInfo gymInfo,
        LocalDateTime createdAt, LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.gymId = gymId;
        this.productList = productList;
        this.gymInfo = gymInfo;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
