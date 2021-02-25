package com.hamryt.helparty.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserDto {

  @NotEmpty(message = "이메일은 필수 입니다")
  private String email;

  private String name;

  private String password;
}
