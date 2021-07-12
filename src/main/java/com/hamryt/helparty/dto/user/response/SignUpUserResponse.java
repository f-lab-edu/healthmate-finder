package com.hamryt.helparty.dto.user.response;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.user.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpUserResponse {

    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String addressCode;
    private String addressDetail;
    private UserType userType;

    @Builder
    public SignUpUserResponse(
        Long id, String email, String name,
        String phoneNumber, String addressCode,
        String addressDetail, UserType userType
    ){
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressCode = addressCode;
        this.addressDetail = addressDetail;
        this.userType = userType;
    }

    public static SignUpUserResponse of(UserDTO user){
        return SignUpUserResponse.builder()
            .id(user.getId())
            .email(user.getEmail())
            .name(user.getName())
            .phoneNumber(user.getPhoneNumber())
            .addressCode(user.getAddressCode())
            .addressDetail(user.getAddressDetail())
            .userType(user.getUserType())
            .build();
    }
}
