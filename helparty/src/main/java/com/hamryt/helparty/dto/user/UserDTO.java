package com.hamryt.helparty.dto.user;

import com.hamryt.helparty.dto.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String email;

    private String password;

    private String name;

    private String phoneNumber;

    private String addressCode;

    private String addressDetail;
    
    private UserType userType;

    @Builder
    public UserDTO(
        String email, String name, String password, String addressCode,
        String addressDetail, String phoneNumber, UserType userType
    ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressCode = addressCode;
        this.addressDetail = addressDetail;
        this.userType = userType;
    }
}
