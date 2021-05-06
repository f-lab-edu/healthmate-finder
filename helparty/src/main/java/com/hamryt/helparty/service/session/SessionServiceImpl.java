package com.hamryt.helparty.service.session;

import com.hamryt.helparty.dto.user.UserDTO;
import com.hamryt.helparty.exception.login.LoginUserDoesNotMatchException;
import com.hamryt.helparty.exception.login.NoLoginAuthException;
import com.hamryt.helparty.service.gym.GymService;
import com.hamryt.helparty.service.user.UserService;
import com.hamryt.helparty.util.SessionKeys;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SessionServiceImpl implements SessionService {
    
    private final Encryptor encryptor;
    private final UserService userService;
    private final GymService gymService;
    private final HttpSession session;
    
    @Transactional(readOnly = true)
    public void validateUser(Long id) {
        String userEmail = (String) session.getAttribute(SessionKeys.LOGIN_USER_EMAIL);
        UserDTO user = userService.getUserById(id);
        if (!user.getEmail().equals(userEmail)) {
            throw new LoginUserDoesNotMatchException(userEmail);
        }
    }
    
    public void sessionValidate() {
        String userEmail = (String) session.getAttribute(SessionKeys.LOGIN_USER_EMAIL);
        
        if (userEmail == null) {
            throw new NoLoginAuthException();
        }
    }
    
    public String getSessionEmail() {
        return (String) session.getAttribute(SessionKeys.LOGIN_USER_EMAIL);
    }
    
}
