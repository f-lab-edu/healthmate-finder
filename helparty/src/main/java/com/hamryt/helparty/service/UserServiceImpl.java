package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.user.UserDeleteRequest;
import com.hamryt.helparty.dto.user.UserDto;
import com.hamryt.helparty.dto.user.request.SignUpRequest;
import com.hamryt.helparty.dto.user.request.UpdateUserReqeust;
import com.hamryt.helparty.dto.user.response.UpdateUserResponse;
import com.hamryt.helparty.exception.user.EmailExistedException;
import com.hamryt.helparty.exception.user.InsertUserFailedException;
import com.hamryt.helparty.exception.user.UpdateFailedException;
import com.hamryt.helparty.exception.user.UserDeleteFailedException;
import com.hamryt.helparty.exception.user.UserNotFoundByIdException;
import com.hamryt.helparty.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final Encryptor encryptor;

    @Transactional
    public void insertUser(SignUpRequest signupRequest) {

        if (isExistsEmail(signupRequest.getEmail())) {
            throw new EmailExistedException(signupRequest.getEmail());
        }

        String encodedPassword = encryptor.encrypt(signupRequest.getPassword());

        UserDto newUser = UserDto.builder()
            .email(signupRequest.getEmail())
            .name(signupRequest.getName())
            .password(encodedPassword)
            .addressCode(signupRequest.getAddressCode())
            .addressDetail(signupRequest.getAddressDetail())
            .build();

        if (userMapper.insertUser(newUser) != 1) {
            throw new InsertUserFailedException(newUser.toString());
        }

    }

    @Transactional
    public UpdateUserResponse updateUser(Long id, UpdateUserReqeust updateUserReqeust) {

        String encodedPassword = encryptor.encrypt(updateUserReqeust.getPassword());

        UpdateUserResponse updateUserResponse = UpdateUserResponse
            .of(id, encodedPassword, updateUserReqeust);

        if (userMapper.updateUser(updateUserResponse) != 1) {
            throw new UpdateFailedException(updateUserReqeust.toString());
        }

        return updateUserResponse;
    }

    @Transactional
    public void deleteUser( UserDeleteRequest userDeleteRequest) {
        String email = userDeleteRequest.getEmail();
        String encodedPassword = encryptor.encrypt(userDeleteRequest.getPassword());

        if(userMapper.deleteUserByEmailAndPassword(email, encodedPassword) != 1){
            throw new UserDeleteFailedException(email);
        }
    }

    @Transactional(readOnly = true)
    public UserDto findUserById(Long id) {
        UserDto userDto = userMapper.findUserById(id);
        if (userDto == null) {
            throw new UserNotFoundByIdException(id);
        }
        return userDto;
    }

    @Transactional(readOnly = true)
    public boolean isExistsEmail(String email) {
        return userMapper.isExistsEmail(email);
    }

    @Transactional(readOnly = true)
    public UserDto findUserByEmailAndPassword(String email, String password) {
        return userMapper.findUserByEmailAndPassword(email, password);
    }


}
