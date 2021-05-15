package com.hamryt.helparty.dto.board.mateboard.request;

import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateMateBoardRequest {
    
    private String gym;
    
    @NotEmpty
    private String content;
    
    @NotEmpty
    private String startTime;
    
    @NotEmpty
    private String endTime;
    
    @Builder
    public CreateMateBoardRequest(
        String gym, String content, String startTime, String endTime
    ) {
        this.gym = gym;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
