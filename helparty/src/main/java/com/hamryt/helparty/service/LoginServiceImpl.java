package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.UserDto;
import com.hamryt.helparty.exception.UserNotExistedException;
import com.hamryt.helparty.util.SessionKeys;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    private final Encryptor encryptor;
    private final UserService userService;
    private final HttpSession session;

    @Transactional
    public UserDto login(String email, String password) {

        String encryptPassword = encryptor.encrypt(password);
        UserDto userDto = userService.findUserByEmailAndPassword(email, encryptPassword);

        if (userDto!=null){
            session.setAttribute(SessionKeys.LOGIN_USER_EMAIL, userDto.getEmail());
        } else {
            throw new UserNotExistedException(email);
        }

        return userDto;
    }

}
