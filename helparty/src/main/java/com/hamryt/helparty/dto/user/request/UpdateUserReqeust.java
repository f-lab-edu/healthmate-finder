package com.hamryt.helparty.dto.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class UpdateUserReqeust {

    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Setter
    private String password;

    @NonNull
    private String phoneNumber;

    @NonNull
    private String addressCode;

    @NonNull
    private String addressDetail;

}
