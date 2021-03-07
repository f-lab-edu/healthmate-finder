package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.UserDto;
import com.hamryt.helparty.exception.UserNotExistedException;
import com.hamryt.helparty.mapper.UserMapper;
import com.hamryt.helparty.util.SessionKeys;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    private final Encryptor encryptor;
    private final UserMapper userMapper;
    private final UserService userService;

    @Transactional
    public UserDto login(String email, String password, HttpSession session) {

        String encryptPassword = encryptor.encrypt(password);

        if (userService.findByEmailAndPassword(email, encryptPassword)) {
            setLoginUserEmail(session, email);
        } else {
            throw new UserNotExistedException(email);
        }

        return userService.findUserByEmail(email);
    }

    public void setLoginUserEmail(HttpSession session, String email) {
        session.setAttribute(SessionKeys.LOGIN_USER_EMAIL, email);
    }

}
