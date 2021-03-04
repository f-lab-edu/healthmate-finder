package com.hamryt.helparty.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserDto {

    public enum Status{
        DEFAULT, DELETE
    }

    @NotEmpty(message = "이메일은 필수 입니다")
    private String email;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;

    private Status status;
}
