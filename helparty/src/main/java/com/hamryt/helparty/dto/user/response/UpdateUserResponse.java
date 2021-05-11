package com.hamryt.helparty.dto.user.response;

import com.hamryt.helparty.dto.user.request.UpdateUserRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class UpdateUserResponse {

    @NonNull
    private Long id;
    private String password;
    private String name;
    private String phoneNumber;
    private String addressCode;
    private String addressDetail;

    @Builder
    public UpdateUserResponse(
        Long id, String password, String name, String addressCode,
        String addressDetail, String phoneNumber
    ) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressCode = addressCode;
        this.addressDetail = addressDetail;
    }

    public static UpdateUserResponse of(Long id, String encodedPassword,
        UpdateUserRequest updateUserRequest) {
        return UpdateUserResponse.builder()
            .id(id)
            .password(encodedPassword)
            .name(updateUserRequest.getName())
            .phoneNumber(updateUserRequest.getPhoneNumber())
            .addressCode(updateUserRequest.getAddressCode())
            .addressDetail(updateUserRequest.getAddressDetail())
            .build();
    }

}
