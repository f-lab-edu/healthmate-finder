package com.hamryt.helparty.mapper;

import com.hamryt.helparty.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {

    UserDto findByIdAndPassword(@Param("email") String email, @Param("password") String password);

    int insertUser(UserDto user);

    boolean isExistsEmail(String email);

}
