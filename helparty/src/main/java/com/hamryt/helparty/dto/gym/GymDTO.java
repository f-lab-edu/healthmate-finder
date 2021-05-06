package com.hamryt.helparty.dto.gym;

import com.hamryt.helparty.dto.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GymDTO {
    
    private Long id;
    
    private String email;
    
    private String gymName;
    
    private String password;
    
    private String phoneNumber;
    
    private String addressCode;
    
    private String addressDetail;
    
    private UserType userType;
    
    @Builder
    public GymDTO(
        String email, String gymName, String password, String phoneNumber,
        String addressCode, String addressDetail, UserType userType
    ) {
        this.email = email;
        this.password = password;
        this.gymName = gymName;
        this.phoneNumber = phoneNumber;
        this.addressCode = addressCode;
        this.addressDetail = addressDetail;
        this.userType = userType;
    }
    
}
