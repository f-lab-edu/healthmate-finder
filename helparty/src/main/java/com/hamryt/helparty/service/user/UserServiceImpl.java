package com.hamryt.helparty.service.user;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.user.UserDTO;
import com.hamryt.helparty.dto.user.request.SignUpUserRequest;
import com.hamryt.helparty.dto.user.request.UpdateUserRequest;
import com.hamryt.helparty.dto.user.response.SignUpUserResponse;
import com.hamryt.helparty.dto.user.response.UpdateUserResponse;
import com.hamryt.helparty.exception.user.EmailExistedException;
import com.hamryt.helparty.exception.user.InsertUserFailedException;
import com.hamryt.helparty.exception.user.UpdateFailedException;
import com.hamryt.helparty.exception.user.UserDeleteFailedException;
import com.hamryt.helparty.exception.user.UserNotFoundByIdException;
import com.hamryt.helparty.mapper.UserMapper;
import com.hamryt.helparty.service.session.Encryptor;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    
    private final UserMapper userMapper;
    private final Encryptor encryptor;
    
    @Transactional
    public SignUpUserResponse insertUser(SignUpUserRequest signupUserRequest) {
        
        UserType.checkUserType(signupUserRequest.getUserType(), UserType.USER);
        
        if (isExistsEmail(signupUserRequest.getEmail())) {
            throw new EmailExistedException(signupUserRequest.getEmail());
        }
        
        String encodedPassword = encryptor.encrypt(signupUserRequest.getPassword());
        
        UserDTO newUser = UserDTO.builder()
            .email(signupUserRequest.getEmail())
            .name(signupUserRequest.getName())
            .password(encodedPassword)
            .phoneNumber(signupUserRequest.getPhoneNumber())
            .addressCode(signupUserRequest.getAddressCode())
            .addressDetail(signupUserRequest.getAddressDetail())
            .userType(signupUserRequest.getUserType())
            .build();
        
        if (userMapper.insertUser(newUser) != 1) {
            throw new InsertUserFailedException(newUser.toString());
        }
        
        return SignUpUserResponse.of(newUser);
    }
    
    @Transactional
    public UpdateUserResponse updateUser(Long id, UpdateUserRequest updateUserRequest) {
        
        String encodedPassword = encryptor.encrypt(updateUserRequest.getPassword());
        
        UpdateUserResponse updateUserResponse = UpdateUserResponse
            .of(id, encodedPassword, updateUserRequest);
        
        if (userMapper.updateUser(updateUserResponse) != 1) {
            throw new UpdateFailedException(id);
        }
        
        return updateUserResponse;
    }
    
    @Transactional
    public void deleteUser(Long id) {
        if (userMapper.deleteUserById(id) != 1) {
            throw new UserDeleteFailedException(id);
        }
    }
    
    @Transactional(readOnly = true)
    public UserDTO findUserById(Long id) {
        return Optional.ofNullable(userMapper.findUserById(id))
            .orElseThrow(() -> new UserNotFoundByIdException(id));
    }
    
    @Transactional(readOnly = true)
    public boolean isExistsEmail(String email) {
        return userMapper.isExistsEmail(email);
        
    }
    
    @Transactional(readOnly = true)
    public UserDTO findUserByEmailAndPassword(String email, String password) {
        return userMapper.findUserByEmailAndPassword(email, password);
    }
    
}