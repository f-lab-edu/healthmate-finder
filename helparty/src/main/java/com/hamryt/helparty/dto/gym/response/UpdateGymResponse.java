package com.hamryt.helparty.dto.gym.response;

import com.hamryt.helparty.dto.gym.request.UpdateGymRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateGymResponse {
    
    private Long id;
    
    private String password;
    
    private String gymName;
    
    private String phoneNumber;
    
    private String addressCode;
    
    private String addressDetail;
    
    @Builder
    public UpdateGymResponse(
        Long id, String password, String gymName,
        String phoneNumber, String addressCode,
        String addressDetail
    ) {
        this.id = id;
        this.password = password;
        this.gymName = gymName;
        this.phoneNumber = phoneNumber;
        this.addressCode = addressCode;
        this.addressDetail = addressDetail;
    }
    
    public static UpdateGymResponse of(Long id, String encodedPassword, UpdateGymRequest updateGymRequest){
        return UpdateGymResponse.builder()
            .id(id)
            .password(encodedPassword)
            .gymName(updateGymRequest.getGymName())
            .phoneNumber(updateGymRequest.getPhoneNumber())
            .addressCode(updateGymRequest.getAddressCode())
            .addressDetail(updateGymRequest.getAddressDetail())
            .build();
    }
    
}
