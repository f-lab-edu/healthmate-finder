package com.hamryt.helparty.dto.board.mateboard.request;

import com.hamryt.helparty.dto.UserType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    
    @NotNull
    private UserType userType;
    
    @Builder
    public CreateMateBoardRequest(
        String gym, String content, String startTime, String endTime, UserType userType
    ) {
        this.gym = gym;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userType = userType;
    }
}
