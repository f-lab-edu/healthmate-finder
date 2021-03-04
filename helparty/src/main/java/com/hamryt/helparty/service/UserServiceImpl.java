package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.UserDto;
import com.hamryt.helparty.exception.EmailExistedException;
import com.hamryt.helparty.exception.UnexpectedResultException;
import com.hamryt.helparty.mapper.UserMapper;
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
    public void insertUser(UserDto userDto) {

        if (isExistsEmail(userDto.getEmail())) {
            throw new EmailExistedException(userDto.getEmail());
        }

        String encodedPassword = encryptor.encrypt(userDto.getPassword());

        UserDto newUser = UserDto.builder()
            .email(userDto.getEmail())
            .name(userDto.getName())
            .password(encodedPassword)
            .build();

        if (userMapper.insertUser(newUser) != 1) {
            throw new UnexpectedResultException(userDto.toString());
        }

    }

    @Transactional
    public boolean isExistsEmail(String email) {
        return userMapper.isExistsEmail(email);
    }
}
