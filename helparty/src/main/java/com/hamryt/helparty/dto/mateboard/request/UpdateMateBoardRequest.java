package com.hamryt.helparty.dto.mateboard.request;

import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UpdateMateBoardRequest {
    
    @NotEmpty
    private String gym;
    
    @NotEmpty
    private String content;
    
    @NotEmpty
    private String startTime;
    
    @NotEmpty
    private String endTime;
    
    @Builder
    public UpdateMateBoardRequest(
        String gym, String content,
        String startTime, String endTime
    ){
        this.gym = gym;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
}


