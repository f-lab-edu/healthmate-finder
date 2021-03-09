package com.hamryt.helparty.mapper;

import com.hamryt.helparty.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {

    boolean findByEmailAndPassword(String email, String password);

    int insertUser(UserDto user);

    boolean isExistsEmail(String email);

    UserDto findUserByEmailAndPassword(String email, String password);

}
