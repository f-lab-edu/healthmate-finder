package com.hamryt.helparty.service;


import com.hamryt.helparty.dto.LoginResponse;
import com.hamryt.helparty.dto.UserDto;
import com.hamryt.helparty.exception.EmailExistedException;
import com.hamryt.helparty.exception.UnexpectedResultException;
import com.hamryt.helparty.mapper.UserMapper;
import com.hamryt.helparty.util.SessionKeys;
import javax.servlet.http.HttpSession;
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

    @Override
    public LoginResponse loginUser(String email, String password, HttpSession session) {

        String encryptPassword = encryptor.encrypt(password);
        UserDto userInfo = userMapper.findByIdAndPassword(email, encryptPassword);

        LoginResponse loginResponse;

        if (userInfo == null) {
            loginResponse = LoginResponse.FAIL;

        } else if (UserDto.Status.DEFAULT.equals(userInfo.getStatus())) {
            loginResponse = LoginResponse.success(userInfo);
            setLoginUserEmail(session, email);

        } else {
            log.error("Login Error");
            throw new RuntimeException("Login Error");
        }

        return loginResponse;
    }

    public void setLoginUserEmail(HttpSession session, String email) {
        session.setAttribute(SessionKeys.LOGIN_USER_EMAIL, email);
    }

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
