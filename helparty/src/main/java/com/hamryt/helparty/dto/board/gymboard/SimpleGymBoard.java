package com.hamryt.helparty.dto.board.gymboard;

import com.hamryt.helparty.dto.board.gymboard.request.CreateGymBoardRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SimpleGymBoard {
    
    private Long id;
    private String title;
    private String content;
    private Long gymProductId;
    private Long gymId;
    
    @Builder
    public SimpleGymBoard(
        String title, String content,
        Long gymProductId, Long gymId
    ) {
        this.title = title;
        this.content = content;
        this.gymProductId = gymProductId;
        this.gymId = gymId;
    }
    
    public static SimpleGymBoard of(CreateGymBoardRequest createGymBoardRequest, Long loginId) {
        return SimpleGymBoard.builder()
            .title(createGymBoardRequest.getTitle())
            .content(createGymBoardRequest.getContent())
            .gymProductId(createGymBoardRequest.getSimpleProduct().getId())
            .gymId(loginId)
            .build();
    }
    
}
