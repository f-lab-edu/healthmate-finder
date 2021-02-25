package com.hamryt.helparty.mapper;

import com.hamryt.helparty.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

  int insertUser(UserDto user);

  Boolean isExistsEmail(String email);

}
