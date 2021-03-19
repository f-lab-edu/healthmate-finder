package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.UserDto;
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
    public UserDto updateUser(Long id, UpdateUserReqeust updateUserReqeust) {

        UserDto userDto = findUserById(id);
        String encodedPassword = encryptor.encrypt(updateUserReqeust.getPassword());

        userDto.setName(updateUserReqeust.getName());
        userDto.setPassword(encodedPassword);
        userDto.setAddressCode(updateUserReqeust.getAddressCode());
        userDto.setAddressDetail(updateUserReqeust.getAddressDetail());

        if (userMapper.updateUser(userDto) != 1) {
            throw new UpdateFailedException(userDto.toString());
        }

        return userDto;
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
