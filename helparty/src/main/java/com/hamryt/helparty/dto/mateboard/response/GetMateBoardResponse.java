package com.hamryt.helparty.dto.mateboard.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hamryt.helparty.dto.mateboard.MateBoardDTO;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMateBoardResponse {
    
    private Long id;
    
    private String userName;
    
    private String userAddress;
    
    private String gym;
    
    private String content;
    
    private String startTime;
    
    private String endTime;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;
    
    @Builder
    public GetMateBoardResponse(
        Long id, String userName, String userAddress,
        String gym, String content, String startTime,
        String endTime, LocalDateTime createdAt, LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.userName = userName;
        this.userAddress = userAddress;
        this.gym = gym;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    
    public static GetMateBoardResponse of(MateBoardDTO mateBoardDTO) {
        return GetMateBoardResponse.builder()
            .id(mateBoardDTO.getId())
            .userName(mateBoardDTO.getUser().getName())
            .userAddress(mateBoardDTO.getUser().getAddressDetail())
            .gym(mateBoardDTO.getGym())
            .content(mateBoardDTO.getContent())
            .startTime(mateBoardDTO.getStartTime())
            .endTime(mateBoardDTO.getEndTime())
            .createdAt(mateBoardDTO.getCreatedAt())
            .modifiedAt(mateBoardDTO.getModifiedAt())
            .build();
    }
}
