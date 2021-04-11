package com.hamryt.helparty.dto.mateboard.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hamryt.helparty.dto.mateboard.MateBoardDTO;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateMateBoardResponse {
    
    private Long id;
    
    private String name;
    
    private String gym;
    
    private String addressDetail;
    
    private String content;
    
    private String startTime;
    
    private String endTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;
    
    @Builder
    public CreateMateBoardResponse(
        Long id, String name, String gym,
        String addressDetail, String content,
        String startTime, String endTime,
        LocalDateTime createAt, LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.name = name;
        this.gym = gym;
        this.addressDetail = addressDetail;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
    
    public static CreateMateBoardResponse of(
        MateBoardDTO mateBoardDTO
    ) {
        return CreateMateBoardResponse.builder()
            .id(mateBoardDTO.getId())
            .name(mateBoardDTO.getUser().getName())
            .gym(mateBoardDTO.getGym())
            .addressDetail(mateBoardDTO.getUser().getAddressDetail())
            .content(mateBoardDTO.getContent())
            .startTime(mateBoardDTO.getStartTime())
            .endTime(mateBoardDTO.getEndTime())
            .createAt(LocalDateTime.now())
            .modifiedAt(LocalDateTime.now())
            .build();
    }
    
}
