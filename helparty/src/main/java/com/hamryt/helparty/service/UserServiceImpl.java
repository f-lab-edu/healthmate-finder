package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.UserDto;
import com.hamryt.helparty.exception.EmailExistedException;
import com.hamryt.helparty.exception.UnexpectedResultException;
import com.hamryt.helparty.mapper.UserMapper;
import com.hamryt.helparty.request.SignUpRequest;
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
    public void insertUser(SignUpRequest request) {

        if (isExistsEmail(request.getEmail())) {
            throw new EmailExistedException(request.getEmail());
        }

        String encodedPassword = encryptor.encrypt(request.getPassword());

        UserDto newUser = UserDto.builder()
            .email(request.getEmail())
            .name(request.getName())
            .password(encodedPassword)
            .addressCode(request.getAddressCode())
            .addressDetail(request.getAddressDetail())
            .build();

        if (userMapper.insertUser(newUser) != 1) {
            throw new UnexpectedResultException(newUser.toString());
        }

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
