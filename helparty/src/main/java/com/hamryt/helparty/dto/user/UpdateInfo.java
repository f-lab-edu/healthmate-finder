package com.hamryt.helparty.dto.user;

import com.hamryt.helparty.request.UpdateUserReqeust;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateInfo {
    @NonNull
    private Long id;
    private String name;
    private String password;
    private String addressCode;
    private String addressDetail;

    @Builder
    public UpdateInfo(Long id, String name, String password, String addressCode, String addressDetail){
        this.id = id;
        this.name = name;
        this.password = password;
        this.addressCode = addressCode;
        this.addressDetail = addressDetail;
    }

    public static UpdateInfo of(Long id, String password, UpdateUserReqeust updateUserReqeust){
        return UpdateInfo.builder()
            .id(id)
            .name(updateUserReqeust.getName())
            .password(password)
            .addressCode(updateUserReqeust.getAddressCode())
            .addressDetail(updateUserReqeust.getAddressDetail())
            .build();
    }

}
