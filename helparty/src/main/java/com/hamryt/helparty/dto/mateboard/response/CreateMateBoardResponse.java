package com.hamryt.helparty.dto.mateboard.response;

import com.hamryt.helparty.dto.mateboard.MateBoardDTO;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateMateBoardResponse {

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
    public CreateMateBoardResponse(
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

    public static CreateMateBoardResponse of(
        MateBoardDTO mateBoardDTO
    ){
        return CreateMateBoardResponse.builder()
            .name(mateBoardDTO.getUser().getName())
            .gym(mateBoardDTO.getGym())
            .address(mateBoardDTO.getUser().getAddressDetail())
            .content(mateBoardDTO.getContent())
            .startTime(mateBoardDTO.getStartTime())
            .endTime(mateBoardDTO.getEndTime())
            .build();
    }

}
