package com.hamryt.helparty.dto.gym.response;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.gym.GymDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpGymResponse {
    
    private Long id;
    
    private String email;
    
    private String gymName;
    
    private String phoneNumber;
    
    private String addressCode;
    
    private String addressDetail;
    
    private UserType userType;
    
    @Builder
    public SignUpGymResponse(
        Long id, String email, String gymName,
        String phoneNumber, String addressCode,
        String addressDetail, UserType userType
    ) {
        this.id = id;
        this.email = email;
        this.gymName = gymName;
        this.phoneNumber = phoneNumber;
        this.addressCode = addressCode;
        this.addressDetail = addressDetail;
        this.userType = userType;
    }
    
    public static SignUpGymResponse of(GymDTO gym) {
        return SignUpGymResponse.builder()
            .id(gym.getId())
            .email(gym.getEmail())
            .gymName(gym.getGymName())
            .phoneNumber(gym.getPhoneNumber())
            .addressCode(gym.getAddressCode())
            .addressDetail(gym.getAddressDetail())
            .userType(gym.getUserType())
            .build();
    }
}
