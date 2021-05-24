package com.hamryt.helparty.dto.board.mateboard.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hamryt.helparty.dto.board.mateboard.request.UpdateMateBoardRequest;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateMateBoardResponse {
    
    private Long id;
    
    private String gym;
    
    private String content;
    
    private String startTime;
    
    private String endTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;
    
    @Builder
    public UpdateMateBoardResponse(
        Long id, String gym, String content,
        String startTime, String endTime,
        LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.gym = gym;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.modifiedAt = modifiedAt;
    }
    
    public static UpdateMateBoardResponse of(
        Long id,
        UpdateMateBoardRequest updateMateBoardRequest
    ) {
        return UpdateMateBoardResponse.builder()
            .id(id)
            .gym(updateMateBoardRequest.getGym())
            .content(updateMateBoardRequest.getContent())
            .startTime(updateMateBoardRequest.getStartTime())
            .endTime(updateMateBoardRequest.getEndTime())
            .modifiedAt(LocalDateTime.now())
            .build();
    }
}
