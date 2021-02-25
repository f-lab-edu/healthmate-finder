package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.UserDto;
import com.hamryt.helparty.exception.EmailExistedException;
import com.hamryt.helparty.exception.UnexpectedInsertException;
import com.hamryt.helparty.mapper.UserMapper;
import com.hamryt.helparty.utill.SecurityUtil;
import com.hamryt.helparty.utill.SecurityUtilImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final SecurityUtil securityUtil;

    @Transactional
    public void insertUser(UserDto userDto) {

        if (isExistsEmail(userDto.getEmail())) {
            throw new EmailExistedException(userDto.getEmail());
        }

        String encodedPassword = securityUtil.encryptSha256(userDto.getPassword());

        UserDto newUser = UserDto.builder()
            .email(userDto.getEmail())
            .name(userDto.getName())
            .password(encodedPassword)
            .build();

        try {
            userMapper.insertUser(newUser);
        } catch (RuntimeException e) {
            log.error(e.toString());
            throw new UnexpectedInsertException(userDto.toString());
        }

    }

    @Transactional
    public boolean isExistsEmail(String email) {
        return userMapper.isExistsEmail(email);
    }
}
