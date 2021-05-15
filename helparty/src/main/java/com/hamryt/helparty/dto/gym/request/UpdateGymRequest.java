package com.hamryt.helparty.dto.gym.request;

import com.hamryt.helparty.dto.UserType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateGymRequest {
    
    
    @NotEmpty
    private String gymName;
    
    @NotEmpty
    private String password;
    
    @NotEmpty
    private String phoneNumber;
    
    @NotEmpty
    private String addressCode;
    
    @NotEmpty
    private String addressDetail;
    
    @NotNull
    private UserType userType;
    
    @Builder
    public UpdateGymRequest(
        String gymName, String password, String phoneNumber,
        String addressCode, String addressDetail, UserType userType
    ) {
        this.gymName = gymName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.addressCode = addressCode;
        this.addressDetail = addressDetail;
        this.userType = userType;
    }
    
}
