package com.hamryt.helparty.dto.gym.request;

import com.hamryt.helparty.dto.UserType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    
}
