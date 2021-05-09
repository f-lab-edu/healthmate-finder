package com.hamryt.helparty.dto.login;

import com.hamryt.helparty.dto.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDTO {
    
    private Long id;
    
    private String email;
    
    private String name;
    
    private String phoneNumber;
    
    private String addressCode;
    
    private String addressDetail;
    
    private UserType userType;
    
    @Builder
    public LoginDTO(
        Long id, String email, String name,
        String phoneNumber, String addressDetail,
        String addressCode, UserType userType
    ){
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressDetail = addressDetail;
        this.addressCode = addressCode;
        this.userType = userType;
    }
    
}
