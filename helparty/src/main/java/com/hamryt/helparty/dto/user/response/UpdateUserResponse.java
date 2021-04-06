package com.hamryt.helparty.dto.user.response;

import com.hamryt.helparty.dto.user.request.UpdateUserReqeust;
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
        UpdateUserReqeust updateUserReqeust) {
        return UpdateUserResponse.builder()
            .id(id)
            .password(encodedPassword)
            .name(updateUserReqeust.getName())
            .phoneNumber(updateUserReqeust.getPhoneNumber())
            .addressCode(updateUserReqeust.getAddressCode())
            .addressDetail(updateUserReqeust.getAddressDetail())
            .build();
    }

}
