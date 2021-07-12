package com.hamryt.helparty.dto.board.mateboard;

import com.hamryt.helparty.dto.user.UserDTO;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MateBoardDTO {
    
    private Long id;
    
    private String gym;
    
    private String content;
    
    private String startTime;
    
    private String endTime;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime modifiedAt;
    
    private Long userId;
    
    private UserDTO user;
    
    @Builder
    public MateBoardDTO(
        String gym, UserDTO user, String content,
        String startTime, String endTime, Long userId,
        LocalDateTime createdAt, LocalDateTime modifiedAt
    ) {
        this.gym = gym;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.userId = userId;
        this.user = user;
    }
}
