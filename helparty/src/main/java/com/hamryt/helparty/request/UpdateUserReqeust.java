package com.hamryt.helparty.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateUserReqeust {

    @NonNull
    private String name;
    @NonNull
    private String password;
    @NonNull
    private String addressCode;
    @NonNull
    private String addressDetail;

}
