package com.hamryt.helparty.service.login;



import com.hamryt.helparty.dto.user.UserDTO;
import com.hamryt.helparty.exception.user.UserNotFoundException;
import com.hamryt.helparty.service.session.Encryptor;
import com.hamryt.helparty.service.user.UserService;
import com.hamryt.helparty.util.SessionKeys;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserLoginServiceImpl implements LoginService<UserDTO>{
    
    private final Encryptor encryptor;
    private final UserService userService;
    private final HttpSession session;
    
    @Transactional(readOnly = true)
    public UserDTO login(String email, String password) {

        String encryptPassword = encryptor.encrypt(password);
        UserDTO userDto = userService.findUserByEmailAndPassword(email, encryptPassword);

        if (userDto != null) {
            session.setAttribute(SessionKeys.LOGIN_USER_EMAIL, userDto.getEmail());
        } else {
            throw new UserNotFoundException(email);
        }

        return userDto;
    }
}
