package com.hamryt.helparty.dto.gym.request;

import com.hamryt.helparty.dto.UserType;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpGymRequest {
    
    @NotNull
    private String email;
    
    @NotNull
    private String gymName;
    
    @NotNull
    private String password;
    
    @NotNull
    private String phoneNumber;
    
    @NotNull
    private String addressCode;
    
    @NotNull
    private String addressDetail;
    
    @NotNull
    private UserType userType;
    
    @Builder
    public SignUpGymRequest(
        String email, String gymName, String password, String phoneNumber,
        String addressCode, String addressDetail, UserType userType
    ) {
        this.email = email;
        this.gymName = gymName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.addressCode = addressCode;
        this.addressDetail = addressDetail;
        this.userType = userType;
    }
    
}
