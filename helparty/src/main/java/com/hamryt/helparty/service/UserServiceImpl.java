package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.user.UserUpdateResponse;
import com.hamryt.helparty.dto.user.UserDto;
import com.hamryt.helparty.exception.user.EmailExistedException;
import com.hamryt.helparty.exception.user.InsertUserFailedException;
import com.hamryt.helparty.exception.user.UpdateFailedException;
import com.hamryt.helparty.exception.user.UserNotFoundByIdException;
import com.hamryt.helparty.mapper.UserMapper;
import com.hamryt.helparty.request.SignUpRequest;
import com.hamryt.helparty.request.UpdateUserReqeust;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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
    public UserUpdateResponse updateUser(Long id, UpdateUserReqeust updateUserReqeust) {

        String encodedPassword = encryptor.encrypt(updateUserReqeust.getPassword());

        UserUpdateResponse userUpdateResponse = UserUpdateResponse
          .of(id, encodedPassword, updateUserReqeust);

        if (userMapper.updateUser(userUpdateResponse) != 1) {
            throw new UpdateFailedException(userUpdateResponse.toString());
        }

        return userUpdateResponse;
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
