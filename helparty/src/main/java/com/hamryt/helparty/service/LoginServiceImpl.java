package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.UserDto;
import com.hamryt.helparty.exception.LoginUserNotFoundException;
import com.hamryt.helparty.exception.UserNotFoundException;
import com.hamryt.helparty.util.SessionKeys;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;

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

        if (userDto != null) {
            session.setAttribute(SessionKeys.LOGIN_USER_EMAIL, userDto.getEmail());
        } else {
            throw new UserNotFoundException();
        }

        return userDto;
    }

    @Transactional(readOnly = true)
    public boolean checkAuth(UserDto userDto) {

        if (!userService.isExistsEmail(userDto.getEmail())) {
            throw new UserNotFoundException();
        }

        String userEmail = getLoginId();

        if (!userEmail.equals(userDto.getEmail())) {
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED) {
            };
        }

        return true;
    }

    @Transactional
    public String getLoginId() {

        String userEmail = (String) session.getAttribute(SessionKeys.LOGIN_USER_EMAIL);
        if (userEmail == null) {
            throw new LoginUserNotFoundException();
        }
        return userEmail;
    }

}
