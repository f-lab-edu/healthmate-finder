package com.hamryt.helparty.mapper;

import com.hamryt.helparty.dto.user.UserDTO;
import com.hamryt.helparty.dto.user.response.UpdateUserResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    
    int insertUser(UserDTO user);
    
    boolean isExistsEmail(String email);
    
    UserDTO findUserByEmailAndPassword(String email, String password);
    
    UserDTO findUserById(Long id);
    
    int updateUser(UpdateUserResponse updateUserResponse);
    
    int deleteUserById(Long id);
}
