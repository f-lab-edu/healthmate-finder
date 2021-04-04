package com.hamryt.helparty.dto.healthmateboard.response;

import com.hamryt.helparty.dto.healthmateboard.HealthMateBoardDTO;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateHealthMateBoardResponse {

    @NotEmpty
    private String name;

    @NotEmpty
    private String gym;

    @NotEmpty
    private String address;

    @NotEmpty
    private String content;

    @NotEmpty
    private String startTime;

    @NotEmpty
    private String endTime;

    @Builder
    public CreateHealthMateBoardResponse(
        String name, String gym,
        String address, String content,
        String startTime, String endTime
    ){
        this.name = name;
        this.gym = gym;
        this.address = address;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static CreateHealthMateBoardResponse of(
        HealthMateBoardDTO healthMateBoardDTO
    ){
        return CreateHealthMateBoardResponse.builder()
            .name(healthMateBoardDTO.getUser().getName())
            .gym(healthMateBoardDTO.getGym())
            .address(healthMateBoardDTO.getUser().getAddressDetail())
            .content(healthMateBoardDTO.getContent())
            .startTime(healthMateBoardDTO.getStartTime())
            .endTime(healthMateBoardDTO.getEndTime())
            .build();
    }

}
