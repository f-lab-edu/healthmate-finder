package com.hamryt.helparty.dto.login;

import com.hamryt.helparty.dto.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDTO {
    
    private Long id;
    
    private String name;
    
    private UserType userType;
    
    @Builder
    public LoginDTO(
        Long id, String name, UserType userType
    ) {
        this.id = id;
        this.name = name;
        this.userType = userType;
    }
    
}
