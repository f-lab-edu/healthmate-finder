package com.hamryt.helparty.mapper;

import com.hamryt.helparty.dto.user.UserUpdateResponse;
import com.hamryt.helparty.dto.user.UserDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    int insertUser(UserDto user);

    boolean isExistsEmail(String email);

    UserDto findUserByEmailAndPassword(String email, String password);

    UserDto findUserById(Long id);

    int updateUser(UserUpdateResponse userUpdateResponse);

    int deleteUserByEmailAndPassword(String email, String password);
}
