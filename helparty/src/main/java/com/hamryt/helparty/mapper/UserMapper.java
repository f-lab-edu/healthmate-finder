package com.hamryt.helparty.mapper;

import com.hamryt.helparty.dto.user.UserDto;
import com.hamryt.helparty.dto.user.response.UpdateUserResponse;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    int insertUser(UserDto user);

    boolean isExistsEmail(String email);

    UserDto findUserByEmailAndPassword(String email, String password);

    UserDto findUserById(Long id);

    int deleteUserByEmailAndPassword(String email, String password);

    int updateUser(UpdateUserResponse updateUserResponse);
}
