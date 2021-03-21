package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.user.UserDto;
import com.hamryt.helparty.exception.login.LoginUserDoesNotMatchException;
import com.hamryt.helparty.exception.login.LoginUserNotFoundException;
import com.hamryt.helparty.exception.user.UserNotFoundException;
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

        if (userDto != null) {
            session.setAttribute(SessionKeys.LOGIN_USER_EMAIL, userDto.getEmail());
        } else {
            throw new UserNotFoundException(userDto.toString());
        }

        return userDto;
    }

    @Transactional(readOnly = true)
    public void checkAuth(String email) {

        String userEmail = getLoginId();

        if (!userEmail.equals(email)) {
            throw new LoginUserDoesNotMatchException(email);
        }
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
