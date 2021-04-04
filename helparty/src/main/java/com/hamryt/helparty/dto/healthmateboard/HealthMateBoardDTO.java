package com.hamryt.helparty.dto.healthmateboard;

import com.hamryt.helparty.dto.user.UserDTO;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HealthMateBoardDTO {

    private Long id;

    private String userEmail;

    private String gym;

    private String content;

    private String startTime;

    private String endTime;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private UserDTO user;

    @Builder
    public HealthMateBoardDTO(
        String userEmail, String gym, UserDTO user,
        String content, String startTime, String endTime
    ){
        this.userEmail = userEmail;
        this.gym = gym;
        this.user = user;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
