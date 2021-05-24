package com.hamryt.helparty.dto.board.gymboard;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GymBoardDTO {
    
    private Long id;
    
    private String title;
    
    private String content;
    
    private Long gymProductId;
    
    private Long gymId;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime modifiedAt;
    
    @Builder
    public GymBoardDTO(
        Long id, String title, String content, Long gymProductId,
        Long gymId, LocalDateTime createdAt, LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.gymProductId = gymProductId;
        this.gymId = gymId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
